package labuladong.stock;

/**
 * 股票问题变种第一题,K=2,同K=any_value
 */
public class Stock5 {
    public static void main(String[] args) {
        int[] prices = {2208, 2230, 2212, 2207, 2233, 2269, 2267};
        int max_k = prices.length / 2;//可买卖次数
        System.out.println(max_value(prices, max_k));
//        System.out.println(max_value1(prices, max_k));
    }

    static int max_value(int[] prices, int max_k) {
        int n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {//第一天
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                //今天不持有,可能是昨天不持有,或者昨天有然后今天卖了
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                //今天持有,可能是昨天持有,或者没有持有,今天买
                //由于买了需要扣除k的次数
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }

        }
        return dp[n - 1][max_k][0];
    }
}
