package labuladong.dynamic_program.practice;

/*
剪绳子2
给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36

状态:绳子长度
选择:将绳子切出一段来
dp数组含义 dp[i]=x 表示长度为i的绳子,乘积最大为x
base case dp[1]=1; dp[2]=1; dp[3]=2
状态转移方程
dp[i] 为 从1到i-2 乘以 dp[i-1]到dp[2] 取出最大的
最后求dp[n]
 */
public class CutRope {
    public int cuttingRope(int n) {
        int dp[] = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i - 1; j++) {
                int sum = Math.max(j * dp[i - j], j * (i - j));
                max = Math.max(sum, max);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public int cuttingRope2(int n) {
        if (n<=3) return n-1;
        int div = n/3;
        int rem = n % 3;
        long result = 1;
        for (int i = 0; i < div; i++) {
            result *= i<div-1 ? 3 : (rem == 2 ? 3*rem : (3+rem));
            if (result >= 1000000007) {
                result = result%1000000007;
            }
        }
        return (int)result;
    }
}
