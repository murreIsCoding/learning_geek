package algorithm.sort;

import org.junit.Test;

/**
 * 二分查找
 * 从小到大重复的数组
 * {1,2,3,4,4,4,4,5,6,7,9,10,10,12}
 */
public class BinarySort {
    @Test
    public void test() {
        int[] test = {1, 2, 3, 4, 4, 4, 4, 5, 6, 7, 9, 10, 10, 10, 12};
        int result = bsearch(test, test.length, 10);
        System.out.println(result);
    }

    public int bsearchFirstMatch(int[] a, int n, int value) {
        int low = 0;
        int hight = n - 1;
        while (low <= hight) {
            int mid = low + ((hight - low) >> 1);
            if (a[mid] > value) {
                hight = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] == value) {
                if (mid == 0 || a[mid - 1] != value) {
                    return mid;
                } else {
                    hight = mid - 1;
                }
            }
        }
        return -1;
    }


    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int hight = n - 1;
        while (low <= hight) {
            int mid = low + ((hight - low) >> 1);
            if (a[mid] > value) {
                hight = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] == value) {
                return mid;
            }
        }
        return -1;
    }
}
