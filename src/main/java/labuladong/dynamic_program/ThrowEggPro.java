package labuladong.dynamic_program;

/**
 * 扔鸡蛋问题 进阶解法
 * ThrowEgg
 * dp[k][m] = n
 * 当前有 k 个鸡蛋，可以尝试扔 m 次鸡蛋
 * 这个状态下，最坏情况下最多能确切测试一栋 n 层的楼
 * <p>
 * 比如说 dp[1][7] = 7 表示：
 * 现在有 1 个鸡蛋，允许你扔 7 次;
 * 这个状态下最多给你 7 层楼，
 * <p>
 * dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1
 * <p>
 * 无论你上楼还是下楼，总的楼层数 = 楼上的楼层数 + 楼下的楼层数 + 1（当前这层楼）。
 * <p>
 * dp[k][m - 1] 就是楼上的楼层数，因为鸡蛋个数 k 不变，也就是鸡蛋没碎，扔鸡蛋次数 m 减一；
 * <p>
 * dp[k - 1][m - 1] 就是楼下的楼层数，因为鸡蛋个数 k 减一，也就是鸡蛋碎了，同时扔鸡蛋次数 m 减一。
 */
public class ThrowEggPro {
    static int superEggDrop(int K, int N) {
        // m 最多不会超过 N 次（线性扫描）
        int[][] dp = new int[K + 1][N + 1];
        // base case:
        // dp[0][..] = 0
        // dp[..][0] = 0
        // Java 默认初始化数组都为 0
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++)
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(superEggDrop(2, 7));
    }
}
