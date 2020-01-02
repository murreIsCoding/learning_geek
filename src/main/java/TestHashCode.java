import java.util.Objects;

public class TestHashCode extends Object {
//    // n 表示数组 array 的长度
//    int find(int[] array, int n, int x) {
//        int i = 0;
//        int pos = -1;
//        for (; i < n; ++i) {
//            if (array[i] == x) {
//                pos = i;
//            }
//        }
//        return pos;
//    }
//
// n 表示数组 array 的长度
//    int find(int[] array, int n, int x) {
//        int i = 0;
//        int pos = -1;
//        for (; i < n; ++i) {
//            if (array[i] == x) {
//                pos = i;
//                break;
//            }
//        }
//        return pos;
//    }
// array 表示一个长度为 n 的数组

    // 代码中的 array.length 就等于 n
//    int n;
//    int[] array = new int[n];
//    int count = 0;
//
//    void insert(int val) {
//        if (count == array.length) {
//            int sum = 0;
//            for (int i = 0; i < array.length; ++i) {
//                sum = sum + array[i];
//            }
//            array[0] = sum;
//            count = 1;
//        }
//
//        array[count] = val;
//        ++count;
//    }
//
// 全局变量，大小为 10 的数组 array，长度 len，下标 i。
    int array[] = new int[10];
    int len = 10;
    int i = 0;

    // 往数组中添加一个元素
    void add(int element) {
        if (i >= len) { // 数组空间不够了
            // 重新申请一个 2 倍大小的数组空间
            int new_array[] = new int[len * 2];
            // 把原来 array 数组中的数据依次 copy 到 new_array
            for (int j = 0; j < len; ++j) {
                new_array[j] = array[j];
            }
            // new_array 复制给 array，array 现在大小就是 2 倍 len 了
            array = new_array;
            len = 2 * len;
        }
        // 将 element 放到下标为 i 的位置，下标 i 加一
        array[i] = element;
        ++i;
    }

}
