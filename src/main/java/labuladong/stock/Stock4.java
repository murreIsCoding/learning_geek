package labuladong.stock;

/**
 * 股票问题变种第一题,K=无穷大 + fee,即能交易无限次 但是卖出需要手续费从收益中扣
 */
public class Stock4 {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 4};
        int fee = 1;
        System.out.println(max_value(prices, fee));
        System.out.println(max_value1(prices, fee));
    }

    static int max_value(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee;
                continue;
            }
            //今天不持有,可能是昨天不持有,或者昨天有然后今天卖了
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //今天持有,可能是昨天持有,或者没有持有,今天买了,还要扣除手续费
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }

    static int max_value1(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            //今天不持有,可能是昨天不持有,或者昨天有然后今天卖了
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            //今天持有,可能是昨天持有,或者昨天没有,今天买了
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }
}
