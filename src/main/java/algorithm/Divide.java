package algorithm;

/**
 * 分治算法的两道习题
 * 二维平面上有 n 个点，如何快速计算出两个距离最近的点对？
 * 有两个 n*n 的矩阵 A，B，如何快速求解两个矩阵的乘积 C=A*B？
 */
public class Divide {

    public static void main(String[] args) {
        int[] arr = {1,  5, 10, 12, 19};
        int left = closePointPair(arr, 0, arr.length - 1);
        System.out.println(left);
    }

    public static int closePointPair(int[] arr, int left, int right) {
        if (left == right) {
            return Integer.MAX_VALUE;
        } else if (left < right) {
            int m = (left + right) / 2;
            int leftDst = closePointPair(arr, left, m);
            int rightDst = closePointPair(arr, m + 1, right);

            //中断条件
            //求中间距离
            int middleDst = arr[m+1] - arr[m];
            int minDis = leftDst < rightDst ? leftDst : rightDst;
            minDis = minDis < middleDst ? minDis : middleDst;
            return minDis;
        }
        return -1;
    }
}
