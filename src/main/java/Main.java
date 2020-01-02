import org.apache.lucene.util.RamUsageEstimator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;


public class Main {
    static  Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws InterruptedException {

        Executors.newFixedThreadPool(100);
        ThreadFactory namedThreadFactory = new ThreadFactory() {
            int i=0;
            public Thread newThread(Runnable r) {
                i++;
                Thread thread = new Thread(r);
                thread.setName("第"+i+"个线程");
                return thread;
            }
        };
        ExecutorService threadPoolExecutor =  new ThreadPoolExecutor(5,
                5,0L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);
//        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
        for (int i =0;i<100;i++){
            threadPoolExecutor.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()  + "say Hello");
                }
            });
        }
    }
}
