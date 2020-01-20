# 使用管道加速Redis查询
#### 请求/回复 协议 和RTT
redis是一个TCP服务器，使用客户/服务端模式，使用请求/回复协议  
所以一个请求完成需要以下几个步骤:
* 一个客户端发送请求给服务端，然后监听socket，通常是通过阻塞的方式，等待服务端的返回
* 服务端处理请求的命令然后发送回复给客户端

例如4个指令就像下面那样：
* Client: INCR X
* Server: 1
* Client: INCR X
* Server: 2
* Client: INCR X
* Server: 3
* Client: INCR X
* Server: 4

客户端和服务端通过网络连接，这样的链接可以是非常快的(回环接口)或者非常慢(两台主机之间通过非常多的跃点才能建立链接)  
这个时间称之为RTT(Round Trip Time往返时间).显而易见的，当客户端需要连续执行许多请求时，这会对性能产生怎样的影响。
（比如给一个list增加很多元素，或者用很多的key来填充数据库)。假如RTT时间是250毫秒(如果互联网连接的速度非常慢的情况),
即使服务器每秒可以处理1000000的次请求，我们每秒也只能处理4条指令。

如果接口使用的是回环接口，RTT变得非常短(加入0.044毫秒)，但是你如果需要连续写入，这仍然很慢。

幸运的是有一种办法可以优化这种情况。

### redis 管道

既然是一个请求/响应式的服务器，那么可以在客户端即使未收到旧的回复的时候旧开始处理新的请求。
这种方式可以通过这样实现：一次发送多个指令，但是不等待每一次的回复，最后一次性再读取回复  
这就被称之为管道，数十年前就广泛使用该技巧了。比如很多POP3协议的实现已经支持这种特性，极大地加快了从服务器下载新电子邮件的过程
redis也很早地支持了这项特性，所以无论你运行的是什么版本，都能使用，下面是使用原始netcat工具的示例

    $ (printf "PING\r\nPING\r\nPING\r\n"; sleep 1) | nc localhost 6379
    +PONG
    +PONG
    +PONG       

这一次我们不需要每次都花费RTT时间，只要一次就能收到3个指令的回复。
非常明确地说，使用pipelining，我们第一个例子的操作顺序如下:
* Client: INCR X
* Client: INCR X
* Client: INCR X
* Client: INCR X
* Server: 1
* Server: 2
* Server: 3
* Server: 4

重要说明：当客户端使用流水线发送命令时，服务器将被迫使用内存将答复排队。
因此，如果您需要使用流水线发送大量命令，则最好以具有合理数量的批处理方式发送它们，例如10k命令，阅读答复，然后再次发送10k命令，依此类推。
速度几乎相同，但是所使用的额外内存将最大为将这10k命令的答复排队所需的最大数量。

## 不只是RTT的因素
流水线传输不仅是一种减少往返时间的延迟成本的方法，它实际上还可以极大地提高您在给定的Redis服务器中每秒可以执行的总操作量。
这是由于以下事实的结果：如果不使用管道，存取数据结构然后回复，单纯看是很省的。
但是从执行套接字I /0的角度来看这非常昂贵。
这涉及到调用read（）和write（）系统调用，这意味着从用户域到内核域的切换。上下文切换是巨大的速度损失。

当使用管道时，许多命令通常通过一个read()系统调用来读取，多个应答通过一个write()系统调用来传递。
因此，每秒执行的总查询数最初随着管道的增长而几乎呈线性增加，最终达到不使用流水线获得的基准的10倍，如您从下图所见：

![对比图](https://redis.io/images/redisdoc/pipeline_iops.png)

### 代码实战例子
在以下基准测试中，我们将使用支持管道的Redis Ruby客户端来测试由于管道带来的速度提高：
````
require 'rubygems'
require 'redis'

def bench(descr)
    start = Time.now
    yield
    puts "#{descr} #{Time.now-start} seconds"
end

def without_pipelining
    r = Redis.new
    10000.times {
        r.ping
    }
end

def with_pipelining
    r = Redis.new
    r.pipelined {
        10000.times {
            r.ping
        }
    }
end

bench("without pipelining") {
    without_pipelining
}
bench("with pipelining") {
    with_pipelining
}
````

运行上述简单脚本将在Mac OS X系统中通过环回接口得到以下数据，由于RTT已经很低，因此RTT的提升影响因素达到了最小(控制变量)：
````
without pipelining 1.185238 seconds
with pipelining 0.250783 seconds
````
如您所见，使用流水线，我们将传输速度提高了五倍。

### 流水线VS脚本
使用Redis脚本（在Redis 2.6或更高版本中可用），可以使用执行服务器端所需的大量工作的脚本来更有效地解决许多流水线用例。
脚本编写的一大优势在于，它能够以最小的延迟读取和写入数据，从而使读取，计算，写入等操作非常快（在这种情况下，
流水处理无济于事，因为客户端需要在读取之前回复读取命令）它可以调用write命令）。


