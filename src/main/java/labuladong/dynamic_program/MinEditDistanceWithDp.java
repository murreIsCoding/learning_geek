package labuladong.dynamic_program;

/**
 * 使用DP TABLE 自底向上方法实现的  最短编辑距离
 */
public class MinEditDistanceWithDp {

    String s1 = "rad";
    String s2 = "apple";


    //DP Table
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];

    public static void main(String[] args) {
        MinEditDistanceWithDp minEditDistanceWithDp = new MinEditDistanceWithDp();
        System.out.println(minEditDistanceWithDp.minDistance());
    }

    int minDistance() {
        //base case 初始化边边
        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= s2.length(); i++) {
            dp[0][i] = i;
        }
        //自底向上解法
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                //如果这两个字符相同，那么直接等于左斜上的值 。 注意charAt要-1 才是正确的字符串的位置
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //如果不相同，那么从“增加，删除，替换” 3种策略中选择一种最小距离的，并且操作数加一
                    dp[i][j] = min(
                            dp[i - 1][j],
                            dp[i][j - 1],
                            dp[i - 1][j - 1]
                    ) + 1;
                }
            }
        }
        //返回最右下角的值
        return dp[s1.length()][s2.length()];
    }

    /**
     * 取多个数的最小值
     *
     * @param s
     * @return
     */
    private int min(int... s) {
        int min = s[0];
        for (int i : s) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }
}
