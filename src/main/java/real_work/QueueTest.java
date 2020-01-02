package real_work;

public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue(5);
        Consumer consumer=new Consumer(queue);
        Producer producer=new Producer(queue);
        producer.produce(100L);
        consumer.comsume();
    }
}
