package labuladong.dynamic_program.practice;

/**
 * 474. 一和零 (中等难度)
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 */
/*
 解题思路:感觉有点像背包问题
 1 状态 可选的列表 0的容量  1的容量
 2 选择 一个数要不要选上
 3 dp[i][m][n] =x 表示对于前i个数,可装m个0,n个1,那它的最大子集的数量是x
 4 base case dp[0][m][n]=0//因为没东西可以选
 5 dp[i][m][n] 的最大取决于
   如果不装那么就等于 dp[i-1][m][n]
   如果装且装得下,那么就取决于dp[i-1][m-第i个数0的数量][n-第i个数0的数量]
   对比这两者,取大的
  6由于dp[i][..][..]的值完全取决于dp[i-1][..][..] 所以可以状态压缩
 */
public class OneAndZero {


    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        //缓存某个数的0的个数
        int[] memo0 = new int[len];
        int[] memo1 = new int[len];
        initMemo(strs, memo0, memo1);
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = m; j >= 0; j--) {//一定要反向遍历
                for (int k = n; k >= 0; k--) {//一定要反向遍历
                    int num0 = memo0[i - 1];
                    int num1 = memo1[i - 1];
                    //装不下,那就只能不装
                    if (j - num0 >= 0 && k - num1 >= 0) {
                        dp[j][k] = Math.max(
                                dp[j][k],//不装
                                dp[j - num0][k - num1] + 1//装
                        );
                    }
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 官方题解 可参考
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm_leetcode(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] count = countzeroesones(s);
            for (int zeroes = m; zeroes >= count[0]; zeroes--)
                for (int ones = n; ones >= count[1]; ones--)
                    dp[zeroes][ones] = Math.max(1 + dp[zeroes - count[0]][ones - count[1]], dp[zeroes][ones]);
        }
        return dp[m][n];
    }



    /**
     * 返回一个数组c[0]为0的个数 c[1]为1的个数
     *
     * @param s 字符串
     * @return 返回一个数组c[0]为0的个数 c[1]为1的个数
     */
    public int[] countzeroesones(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - '0']++;
        }
        return c;
    }

    private void initMemo(String[] strs, int[] memo0, int[] memo1) {
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int num1 = 0;
            for (char c : str.toCharArray()) {
                if (c == '1') {
                    num1++;
                }
            }
            memo0[i] = str.length() - num1;
            memo1[i] = num1;
        }
    }


}
