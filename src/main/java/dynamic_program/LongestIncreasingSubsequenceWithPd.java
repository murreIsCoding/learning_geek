package dynamic_program;

/**
 * LIS 最长递增子序列问题
 * 使用动态规划解法
 */
public class LongestIncreasingSubsequenceWithPd {
    //问题数组
    static int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        /**
         * pd table
         * index同数组中的下标
         * val 表示到该数字时,最长子序列的长度
         *
         * base case pd[0] == 1
         * 状态转移方程 如果知道了pd[0]~pd[n],求pd[n+1]
         * 那么只要在 nums[0~n]中找出val小于nums[n+1]的,并找出他们对应的pd,取出最大的,则该位置的pd为最大的pd+1
         * 最后取出pd[]中最大的值
         * 复杂度为O(n²)
         */
        int[] pd = new int[nums.length];
        //base case
        pd[0] = 1;
        int maxPd = 1;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int currentMaxPd = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < num) {
                    if (pd[j] > currentMaxPd) {
                        currentMaxPd = pd[j];
                    }
                }
            }
            pd[i] = currentMaxPd + 1;
            if (pd[i] > maxPd) {
                maxPd = pd[i];
            }
        }
        return maxPd;
    }
}
