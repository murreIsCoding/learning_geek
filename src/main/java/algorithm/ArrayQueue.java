package algorithm;

/**
 * 数组实现的队列
 * 先入先出
 * 对头出列
 * 队尾入列
 */
public class ArrayQueue {
    //数组
    String[] array;
    //头
    int head;
    //尾
    int tail;
    //数组大小
    int n;

    public ArrayQueue(int n) {
        array = new String[n];
        head = 0;
        tail = 0;
        this.n = n;
    }

    /**
     * 入队
     *
     * @param a
     * @return
     */
    public boolean enqueue(String a) {
        if (tail - head == n){
            return false;
        }
        if (tail == n) {
            for (int i = head, j = 0; i < tail; i++, j++) {
                array[j] = array[i];
            }
            tail = tail-head;
            head = 0 ;
        }
        array[tail] = a;
        tail++;
        return true;
    }

    public String dequeue() {
        if (head == tail) {
            return null;
        }
        String result = array[head];
        head++;
        return result;
    }
    public void print(){
        StringBuffer sb = new StringBuffer();
        for (int i = head ; i<tail ; i++){
            sb.append(array[i]);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }
}
