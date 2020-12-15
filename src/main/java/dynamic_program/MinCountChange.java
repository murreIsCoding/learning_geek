package dynamic_program;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小零钱问题
 */
public class MinCountChange {
    static int[] coins;
    //备忘录
    static Map<Integer, Integer> memo = new HashMap<>();

    /**
     * 使用动态规划来解决问题
     * 自底向上
     *
     * @param coins
     * @param amount
     * @return
     */
    static int coinChangeUseDp(int[] coins, int amount) {
        //初始化数组，下标表示amount，里面的值表示需要的最小硬币数
        int[] dp = new int[amount + 1];
        //初始化为最大值
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }
        //base case
        dp[0] = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                //如果目标小于面值，直接跳过
                if (i < coin) {
                    continue;
                } else {
                    //当前  和  目标-coin的解 +1  对比
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[amount];
        }
    }


    /**
     * @param coins  面值
     * @param amount 目标总额
     * @return 个数
     */
    static int coinChange(int[] coins, int amount) {
        MinCountChange.coins = coins;
        return dp(amount);
    }

    static int dp(int n) {
        //先从备忘录中寻找
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        //base case
        if (n == 0) return 0;
        if (n < 0) return -1;

        //res为当前最小的解，默认始化为int最大值
        int res = Integer.MAX_VALUE;

        for (int coin : coins) {
            int subproblem = dp(n - coin);
            //如果子问题无解，那么继续
            if (subproblem == -1) {
                continue;
            } else {
                //求当前res 和 子解+1  ，取比较小的
                res = Math.min(res, 1 + subproblem);
            }
        }
        //解
        int problem;
        if (res == Integer.MAX_VALUE) {
            problem = -1;//没有解
        } else {
            problem = res;
        }
        //记入备忘录
        memo.put(n, problem);
        return problem;
    }

    public static void main(String[] args) {
        int[] coins = { 2, 5};
        System.out.println(coinChange(coins, 1100));
        System.out.println(coinChangeUseDp(coins, 1100));
    }
}
