package design_pattern.demo2.performance_monitoring;


public class Demo {
    public static void main(String[] args) throws InterruptedException {
        MetricsStorage storage = new RedisMetricsStorage();
        MetricsCollector collector = new MetricsCollector(storage);
        ConsoleReporter consoleReporter = new ConsoleReporter(storage);
        consoleReporter.startRepeatedReport(5, 30);
        EmailReporter emailReporter = new EmailReporter(storage);
        emailReporter.startDailyReport();

        while (true) {
            collector.recordRequest(new RequestInfo("register", 123, System.currentTimeMillis()));
            collector.recordRequest(new RequestInfo("login", 23, System.currentTimeMillis()));
            Thread.sleep(10*1000);
        }

//        collector.recordRequest(new RequestInfo("register", 223, current+10000));
//        collector.recordRequest(new RequestInfo("register", 323, current+20000));
//
//        collector.recordRequest(new RequestInfo("login", 1223, current+10000));

//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}