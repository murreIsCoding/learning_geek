import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Stream {
    @Test
    public void test() {
        List<String> stringList = new ArrayList();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb11");
        stringList.add("aaa1");
        stringList.add("bbb3111");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");
//        stringList.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        // 测试 InsertSort (排序)
//        stringList
//                .stream()
//                .sorted((a, b) -> a.length() > b.length() ? 1 : -1)
//                .forEach(System.out::println);

        Optional<String> reduced  = stringList
                .stream()
                .reduce((s1, s2) -> s1+"#"+s2);
        reduced.ifPresent(s -> System.out.println(s));
    }


    @Test
    public void test2(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

//        //串行排序
//        long t0 = System.nanoTime();
//        long count = values.stream().sorted().count();
//        System.out.println(count);
//
//        long t1 = System.nanoTime();
//
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        System.out.println(String.format("sequential sort took: %d ms", millis));

        //并行排序
        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }
}
