package redis;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K,V> extends LinkedHashMap<K,V> {
    private final int CACHE_SIZE;

    /**
     * @param cache_size 最多存储多少个元素
     */
    public LRU(int cache_size) {
        super((int)Math.ceil(cache_size/0.75)+1,0.75f ,true);
        CACHE_SIZE = cache_size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //当map中的数据大于指定的缓存个数的时候，旧自动删除最老的数据
        return size()>CACHE_SIZE;
    }
}