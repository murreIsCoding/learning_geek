package labuladong.dynamic_program.practice;

/*
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 * 示例 1：
 * 输入：N = 10, K = 1, W = 10
 * 输出：1.00000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 示例 2：
 * 输入：N = 6, K = 1, W = 10
 * 输出：0.60000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
 * 示例 3：
 * 输入：N = 21, K = 17, W = 10
 * 说明:当她从[1,10]的卡堆里抽卡累积,她的策略时当她的分数>=17分就停止抽卡,那么它的分数<=21的概率是0.732278
 * 概率=不超过的情况/(不超过的情况+超过的情况)
 * 不超过的情况(10,7)(11,7)(12,7)(13,7)(14,7)(14,6)(13,7)...
 * 输出：0.73278
 * 提示：
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000

    dp[x] 表示从得分为 x 的情况开始游戏并且获胜的概率，目标是求 dp[0] 的值
    当K<=x<=min(N,K-1+W)时 dp[x] = 1
    当x>min(N,K-1+W)时 dp[x] = 0
    当 0<=x<K时  x=(dp(x+1)+dp(x+2)+dp(x+3)+...+dp(x+W)) / W
 *
 * 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
 * 此问题的判断限制时间已经减少。
 */


public class Point21 {
    /**
     * 时间复杂度 N+KW
     *
     * @param N
     * @param K
     * @param W
     * @return
     */
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        double[] dp = new double[K + W];
        int min = Math.min(N, K - 1 + W);
        for (int i = K; i <= min; i++) {
            dp[i] = 1.0;
        }
        for (int i = K - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = 1; j <= W; j++) {
                sum += dp[i + j];
            }
            dp[i] = sum / W;
        }
        return dp[0];
    }

    public double new21Game2(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        double[] dp = new double[K + W];
        int min = Math.min(N, K - 1 + W);//不超过N或者不超过K-1+W,取最小的
        for (int i = K; i <= min; i++) {
            dp[i] = 1.0;
        }
        dp[K - 1] = 1.0 * Math.min(N - K + 1, W) / W;
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + W + 1] - dp[i + 1]) / W; //新的转移方程
        }

        return dp[0];
    }
}
