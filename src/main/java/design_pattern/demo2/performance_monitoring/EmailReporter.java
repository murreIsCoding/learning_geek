package design_pattern.demo2.performance_monitoring;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// EmailReporter类的代码实现与Console类似，就没有给出了
public class EmailReporter {
    private MetricsStorage metricsStorage;
    private ScheduledExecutorService executor;

    public EmailReporter(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startDailyReport() {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //...
            }
        }, 0, 24, TimeUnit.HOURS);
    }
}