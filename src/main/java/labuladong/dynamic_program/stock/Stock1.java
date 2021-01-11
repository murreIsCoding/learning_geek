package labuladong.dynamic_program.stock;

/**
 * 股票问题变种第一题,K=1,即只能交易一次
 */
public class Stock1 {
    public static void main(String[] args) {
        int[] prices = {1, 7, 2, 6, 3, 6, 4, 5};
        System.out.println(max_value(prices));
        System.out.println(max_value1(prices));
    }

    static int max_value(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            //对i-1<0做特殊处理,base case
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            //今天不持有,可能是昨天不持有,或者昨天有然后今天卖了
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //今天持有,可能是昨天持有,或者昨天没有,今天买了,那么今天的price就是-price
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]);
        }
        return dp[n - 1][0];
    }

    static int max_value1(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            //今天不持有,可能是昨天不持有,或者昨天有然后今天卖了
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            //今天持有,可能是昨天持有,或者昨天没有,今天买了
            dp_i_1 = Math.max(dp_i_1, 0 - prices[i]);
        }
        return dp_i_0;
    }
}
