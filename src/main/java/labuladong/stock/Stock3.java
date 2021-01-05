package labuladong.stock;

/**
 * 股票问题变种第一题,K=无穷大 + cooldown,即能交易无限次 但是卖出之后需要等一天才能交易
 */
public class Stock3 {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 4};
        System.out.println(max_value(prices));
        System.out.println(max_value1(prices));
    }

    static int max_value(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            //今天不持有,可能是昨天不持有,或者昨天有然后今天卖了
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //今天持有,可能是昨天持有,或者没有持有,今天买了,但是要冷却一天,所以应该是前天的
            //如果i-2==-1,那么只能继续持有
            if (i - 2 == -1) {
                dp[i][1] = dp[i - 1][1];
            } else {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }

        }
        return dp[n - 1][0];
    }

    static int max_value1(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0;//代表dp[i-2][0] 缓存前天的不持有的
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            //今天不持有,可能是昨天不持有,或者昨天有然后今天卖了
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            //今天持有,可能是昨天持有,或者昨天没有,今天买了
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }
}
