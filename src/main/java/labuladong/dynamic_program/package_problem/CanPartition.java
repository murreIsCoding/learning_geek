package labuladong.dynamic_program.package_problem;

/*
  leetcode 416题
  给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
  注意:
  每个数组中的元素不会超过 100
  数组的大小不会超过 200
  示例 1:
  输入: [1, 5, 11, 5]
  输出: true
  解释: 数组可以分割成 [1, 5, 5] 和 [11].
   
  示例 2:
  输入: [1, 2, 3, 5]
  输出: false
  解释: 数组不能分割成两个元素和相等的子集.
 */

/**
 * 解题思路:背包问题变种,相当于求能装SUM/2重量的背包 , 在前N个物品里,能找到刚好能装满背包的几个东西
 * 1状态 背包的容量 可选择的物品(前i个)
 * 2选择:装进背包 或者 不装进背包
 * 3dp数组含义 dp[i][j]=x 表示 对于前i个物品,当前容量为j时, 如果x为true,表示刚好能装满, x为false表示无法装满
 * 对于本命题表示,对于前i个数字,当前和为j, 如果x为true,表示有子集可以刚好凑出来, x为false表示无法凑出来
 * 4base case dp[0][...]=false (因为没有物品 无法凑出来) dp[1~N][0]=true (因为背包容量为0,那么不用物品就能凑出来)
 * 5状态转移方程:
 * 如果不把第i个物品装进背包,dp[i][j]的结果就取决于dp[i-1][j]能不能填满背包
 * 如果把第i个物品装进背包,那么dp[i][j]的结果就取决于dp[i-1][j-num[i-1]]能不能填满背包 (num[i-1]表示当前的数)
 */
public class CanPartition {
    boolean canPartition(int[] nums) {
        int sum = sum(nums);
        //如果无法被整除直接返回false
        if (sum % 2 != 0) {
            return false;
        }
        int half = sum / 2;
        //创建DP数组
        boolean[][] dp = new boolean[nums.length + 1][half + 1];
        //初始化base case
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= half; j++) {
                //判断是否能装下,如果根本装不下就只能不装了
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //使用逻辑或,只要有一种情况能装下就是能装下
                    dp[i][j] = Boolean.logicalOr(
                            dp[i - 1][j],//不装
                            dp[i - 1][j - nums[i - 1]]//装,注意 i-1表示当前的数
                    );
                }
            }
        }
        return dp[nums.length][half];
    }

    /**
     * 装填压缩版本,由于dp[i][j]的状态都是由dp[i-1][j]的状态得来的,所以二维数组可以压缩成一维数组,只需要使用dp[j]来表示dp[i-1][j]即可
     *
     * @param nums
     * @return
     */
    boolean canPartitionStateCompression(int[] nums) {
        int sum = sum(nums);
        //如果无法被整除直接返回false
        if (sum % 2 != 0) {
            return false;
        }
        int half = sum / 2;
        //创建DP数组
        boolean[] dp = new boolean[half + 1];
        //初始化base case
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            //注意,如果是状态压缩情况下,必须从后面开始往前遍历,否则将出错,因为上一次的结果已经被这一次的所覆盖,而反向遍历不会出现这个情况
            for (int j = half; j > 0; j--) {
                //判断是否能装下,如果根本装不下就只能不装了--即沿用上一次的结果所以无需处理
                if (j - nums[i - 1] >= 0) {
                    //使用逻辑或,只要有一种情况能装下就是能装下
                    dp[j] = Boolean.logicalOr(
                            dp[j],//不装
                            dp[j - nums[i - 1]]//装,注意 i-1表示当前的数
                    );
                }
            }
        }
        return dp[half];
    }

    private int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
