package labuladong.dynamic_program;

/**
 * rap
 * 变成
 * apple
 * 需要5步
 * <p>
 * 伪代码
 * if s1[i] == s2[j]:
 * 啥都别做（skip）
 * i, j 同时向前移动
 * else:
 * 三选一：
 * 插入（insert）
 * 删除（delete）
 * 替换（replace）
 */
public class MinEditDistance {
    static String s1 = "我是一个大傻逼";
    static String s2 = "我是两个大饼";

    public static void main(String[] args) {
        System.out.println(minDistance());
    }



    static int minDistance() {
        return dp(s1.length() - 1, s2.length() - 1);
    }

    /**
     * 递归函数
     *
     * @param i s1的下标
     * @param j s2的下标
     * @return 最小编辑距离
     */
    private static int dp(int i, int j) {
        //base case 当某个字符串结束时，直接返回另一个字符串还剩下的长度
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        //如果两个字符相等，那么啥也不做，同时往左退一格
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp(i - 1, j - 1);
        } else {
            //如果不相等，那么穷举3种方式 1插入 2删除 3替换 ，然后取出编辑距离最少的那个
            return min(
                    //插入
                    dp(i, j - 1) + 1,
                    //删除
                    dp(i - 1, j) + 1,
                    //替换
                    dp(i - 1, j - 1) + 1
            );
        }
    }

    /**
     * 取多个数的最小值
     *
     * @param s
     * @return
     */
    private static int min(int... s) {
        int min = s[0];
        for (int i : s) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }
}
