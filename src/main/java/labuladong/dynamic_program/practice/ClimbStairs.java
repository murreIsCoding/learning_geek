package labuladong.dynamic_program.practice;

/**
 * 746. 使用最小花费爬楼梯
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
 * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
 * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 * 示例 1：
 * 输入：cost = [10, 15, 20]
 * dp   = [25, 15, 20]
 * 输出：15
 * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
 * 示例 2：
 * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * dp   = [ 6  5  5 ]
 * 输出：6
 * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
 * 提示：
 * cost 的长度范围是 [2, 1000]。
 * cost[i] 将会是一个整型数据，范围为 [0, 999] 。
 */
/*
 分析思路:
 1 状态:当前所处的位置 当前选了跳还是不跳
 2 选择:选择走下一级还是下下级
 3 dp数组的含义dp[i][0] = x 表示在第i级且当前状态是跳,开始走到终点,需要花费的最小体力值  ,1相反
 4 base case 如果只有1级,那么只能选最后一级 N=最后一级 dp[N][0] = 0  dp[N][1]=cost[N]
 5 状态转移方程
   如果当前不跳 那么=下一跳跳
   如果当前要跳 那么=min(下一跳跳,下一跳不跳)

   //状态压缩
   由于dp[i]的值完全取决于与dp[i+1]的值,所以可以进行状态压缩成一维
 */
public class ClimbStairs {
    int climbStairs(int[] cost) {
        int length = cost.length;
        //dp数组
        int[][] dp = new int[length][2];
        for (int i = length - 1; i >= 0; i--) {
            if (i == length - 1) {
                dp[i][0] = 0;
                dp[i][1] = cost[i];
            } else {
                dp[i][0] = dp[i + 1][1];
                dp[i][1] = Math.min(dp[i + 1][1], dp[i + 1][0]) + cost[i];
            }
        }
        return Math.min(dp[0][0], dp[0][1]);
    }

    int climbStairs_compress(int[] cost) {
        int length = cost.length;
        int dp_0 = 0;
        int dp_1 = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (i == length - 1) {
                dp_0 = 0;
                dp_1 = cost[i];
            } else {
                int temp0 = dp_0;
                int temp1 = dp_1;
                dp_0 = dp_1;
                dp_1 = Math.min(temp0, temp1) + cost[i];
            }
        }
        return Math.min(dp_0, dp_1);
    }

}
