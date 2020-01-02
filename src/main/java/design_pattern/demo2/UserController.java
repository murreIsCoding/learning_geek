package design_pattern.demo2;


import java.util.Random;
import java.util.concurrent.TimeUnit;

//应用场景：统计下面两个接口(注册和登录）的响应时间和访问次数
public class UserController {
    private Metrics metrics = new Metrics();

    public UserController() {
        metrics.startRepeatedReport(5, TimeUnit.SECONDS);
    }

    public void register(UserVo user) throws InterruptedException {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("regsiter", startTimestamp);
        //...
        Random random = new Random();
        Thread.sleep(random.nextInt(10)*1000);
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register", respTime);
    }

    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("login", startTimestamp);
        //...
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("login", respTime);
        return new UserVo();
    }
}