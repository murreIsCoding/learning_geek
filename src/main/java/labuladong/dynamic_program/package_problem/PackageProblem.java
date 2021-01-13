package labuladong.dynamic_program.package_problem;

import cn.hutool.core.util.ArrayUtil;

/**
 * 给你一个可装载重量为W的背包和N个物品，每个物品有重量和价值两个属性。
 * 其中第i个物品的重量为wt[i]，价值为val[i]，现在让你用这个背包装物品，最多能装的价值是多少
 * 举个简单的例子，输入如下：
 * N = 3, W = 4
 * wt = [2, 1, 3]
 * val = [4, 2, 3]
 * 算法返回 6，选择前两件物品装进背包，总重量 3 小于W，可以获得最大价值 6。
 * 这个题目中的物品不可以分割，要么装进包里，要么不装，不能说切成两块装一半。这也许就是 0-1 背包这个名词的来历。
 * 状态转移方程
 */
public class PackageProblem {
    static int[] wt = {2, 1, 3};
    static int[] val = {4, 2, 3};

    public static void main(String[] args) {
        int N = 3, W = 4;

    }

    /**
     * for 状态1 in 状态1的所有取值：
     * for 状态2 in 状态2的所有取值：
     * for ...
     * dp[状态1][状态2][...] = 择优(选择1，选择2...)
     *
     * @param W
     * @param N
     * @param wt
     * @param val
     * @return
     */
    int knapsack(int W, int N, int[] wt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                //如果当前背包装不下
                if (w - wt[i - 1] < 0) {
                    // 当前背包容量装不下，只能选择不装入背包
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 装入或者不装入背包，择优
                    dp[i][w] = Math.max(
                            dp[i - 1][w - wt[i - 1]] + val[i - 1],//装
                            dp[i - 1][w]  //不装
                    );
                }
            }
        }
        System.out.println(ArrayUtil.toString(dp));
        return dp[N][W];
    }

}
