import org.apache.lucene.util.RamUsageEstimator;

import java.util.*;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        LinkedHashSet<Integer> linkedHashMap = new LinkedHashSet<>(10000000);
        HashSet<Integer> hashMap = new HashSet<>(10000000);
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10000000; i++) {
            linkedHashMap.add(i);
            hashMap.add(i);
            linkedList.add(i);
        }
        long t1 = System.currentTimeMillis();
        for (Integer key : linkedHashMap) {

        }
        long t2 = System.currentTimeMillis();
        for (Integer key : hashMap) {

        }
        long t3 = System.currentTimeMillis();
        for (Integer i : linkedList){

        }
        long t4 = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println(t3-t2);
        System.out.println(t4-t3);
    }
}
