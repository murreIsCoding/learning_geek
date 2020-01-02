package design_pattern.demo2.performance_monitoring;

import java.util.List;
import java.util.Map;

public interface MetricsStorage {
    void saveRequestInfo(RequestInfo requestInfo);
    List<RequestInfo> getRequestInfosByDuration(String apiName, long startTimestamp, long endTimestamp);
    Map<String, List<RequestInfo>> getAllRequestInfosByDuration(long startTimestamp, long endTimestamp);
}