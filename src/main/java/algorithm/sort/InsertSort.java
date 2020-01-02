package algorithm.sort;

import org.junit.Test;

public class InsertSort {
    // 插入排序，a 表示数组，n 表示数组大小
    public void insertionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            a[j+1] = value; // 插入数据
        }
    }

    @Test
    public void test(){
        int[] temp = {5,4,3,2,1};
        insertionSort(temp , 5);
    }

}
