package labuladong.dynamic_program;

/**
 * 斐波那契数列
 * int fib(int N) {
 * if (N < 1) return 0;
 * if (N == 1 || N == 2) return 1;
 * vector<int> dp(N + 1, 0);
 * // base case
 * dp[1] = dp[2] = 1;
 * for (int i = 3; i <= N; i++)
 * dp[i] = dp[i - 1] + dp[i - 2];
 * return dp[N];
 * }
 */
public class Fibonacci {
    static int fib(int N) {
        if (N < 1) return 0;
        if (N == 1 || N == 2) return 1;
        int[] dp = new int[N + 1];
        // base case
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= N; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[N];
    }

    public static void main(String[] args) {
        System.out.println(fib(20));
    }
}
