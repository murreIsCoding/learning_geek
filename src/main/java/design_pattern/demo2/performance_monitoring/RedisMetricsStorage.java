package design_pattern.demo2.performance_monitoring;

import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class RedisMetricsStorage implements MetricsStorage {
    static JedisPool pool;

    public RedisMetricsStorage() {
        synchronized (RedisMetricsStorage.class) {
            if (pool == null) {
                pool = new JedisPool(new JedisPoolConfig(), "localhost");
                System.out.println("redis连接完成");
            }
        }

    }

    //属性和构造函数等...
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Gson gson = new Gson();
            jedis.zadd(requestInfo.getApiName(), requestInfo.getTimestamp(), gson.toJson(requestInfo));
        } finally {
            // You have to close jedis object. If you don't close then
            // it doesn't release back to pool and you can't get a new
            // resource from pool.
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<RequestInfo> getRequestInfosByDuration(String apiName, long startTimestamp, long endTimestamp) {
        //...
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getAllRequestInfosByDuration(long startTimestamp, long endTimestamp) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Gson gson = new Gson();
            Set<String> keyset = jedis.keys("*");
            Map<String, List<RequestInfo>> map = new LinkedHashMap<>();
            for (String key : keyset){
                Set<String> strs = jedis.zrangeByScore(key,startTimestamp,endTimestamp);
                List<RequestInfo> requestInfos = new ArrayList<>();
                for (String str : strs){
                    RequestInfo requestInfo = gson.fromJson(str,RequestInfo.class);
                    requestInfos.add(requestInfo);
                }
                map.put(key,requestInfos);
            }
            return map;
        } finally {
            // You have to close jedis object. If you don't close then
            // it doesn't release back to pool and you can't get a new
            // resource from pool.
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}