package algorithm;

import org.junit.Test;

public class ArrayStack {
    //数组
    String[] array;
    //元素个数
    int count;
    //数组大小
    int n;

    public ArrayStack(int n) {
        array = new String[n];
        count = 0;
        this.n = n;
    }

    public boolean push(String a) {
        boolean result;
        if (count + 1 == n) {
            result = false;
        } else {
            count++;
            array[count] = a;
            result = true;
        }
        System.out.println("push " + a + ":" + result);
        return result;
    }

    public String pop() {
        String result;
        if (count == 0) {
            result = null;
        } else {
            result = array[count];
            count--;
        }
        System.out.println("pop :" + result);
        return result;
    }


}
