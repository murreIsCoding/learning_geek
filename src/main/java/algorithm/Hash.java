package algorithm;

import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

public class Hash {
    static final int hash(Object key) {
        int h;
        h = key.hashCode();
        int a2 = h >>> 16;
        int hashcode = h ^ a2;
        return hashcode;

    }

    class Some{
        int i ;
        public Some(int i){
            this.i = i ;
        }

        @Override
        public int hashCode() {
            return i%10;
        }
    }
    @Test
    public void test() {
        int n = 1000000;
        int length = 8;
        int r1 = n % length;
        int r2 = n & (length - 1);
        System.out.println(r1);
        System.out.println(r2);
    }

    @Test
    public void testHashCode(){
        HashMap hashMap = new HashMap(3);
        hashMap.put(new Some(11),"11");
        hashMap.put(new Some(21),"21");
        hashMap.put(new Some(31),"31");
        hashMap.put(new Some(41),"41");
        hashMap.put(new Some(51),"51");
        hashMap.put(new Some(61),"61");
        hashMap.put(new Some(71),"71");
    }
}
