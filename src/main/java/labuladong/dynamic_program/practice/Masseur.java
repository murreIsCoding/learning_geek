package labuladong.dynamic_program.practice;

/**
 * 面试题 17.16. 按摩师
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
 * 在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * Murre补充:这里预约表示按摩时长,时间越长就能赚越多钱
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例 1：
 * <p>
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 * 示例 2：
 * <p>
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 * 示例 3：
 * <p>
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 */
/*
思路分析: 这不就是打家劫舍问题.
1状态: 当前预约选不选
2选择: 跳过还是接下个需求
3dp[i][0]表示如果第i个预约不接,能得到最大的时长, dp[i][1]表示第i个预约接,能得到的最大时长
4base case :如果只有一个预约 那么dp[i][0]=0 dp[i][1]=nums[i];
5状态转移:dp[i][0]取决于max(dp[i+1][0]或dp[i+1][1]) dp[i][1]=dp[i+1][0]+num[i]
6状态压缩:由于dp[i]的数据完全只取决于额dp[i+1]的数据,所以可以进行状态压缩
 */
public class Masseur {
    public int massage(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[n][2];
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                dp[i][0] = 0;
                dp[i][1] = nums[i];
            } else {
                dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][1]);//不接
                dp[i][1] = dp[i + 1][0] + nums[i];//接
            }
        }
        return Math.max(dp[0][0], dp[0][1]);
    }

    public int massage_compress(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int dp_0 = 0;
        int dp_1 = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                dp_0 = 0;
                dp_1 = nums[i];
            } else {
                int temp0 = dp_0;
                int temp1 = dp_1;
                dp_0 = Math.max(temp0, temp1);//不接
                dp_1 = temp0 + nums[i];//接
            }
        }
        return Math.max(dp_0, dp_1);
    }
}
