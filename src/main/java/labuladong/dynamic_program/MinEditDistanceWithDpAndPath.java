package labuladong.dynamic_program;

import java.util.LinkedList;

/**
 * 使用DP TABLE 自底向上方法实现的  最短编辑距离
 * 升级版，可以显示路径
 */
public class MinEditDistanceWithDpAndPath {

    //操作次数
    static int ops_times;
    String s1 = "今天是个上分的好日子";
    String s2 = "今天是个掉分的好日子";
    //DP Table
    Node[][] dp = new Node[s1.length() + 1][s2.length() + 1];

    public static void main(String[] args) {
        MinEditDistanceWithDpAndPath minEditDistanceWithDp = new MinEditDistanceWithDpAndPath();
        ops_times = minEditDistanceWithDp.minDistance().val;
        System.out.println("最少需要" + ops_times + "个处理步骤");
        minEditDistanceWithDp.printPath();
    }

    Node minDistance() {
        //base case 初始化边边
        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = new Node(i, Node.CHOICE_DELETE);
        }
        for (int i = 0; i <= s2.length(); i++) {
            dp[0][i] = new Node(i, Node.CHOICE_INSERT);
        }
        //自底向上解法
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                //如果这两个字符相同，那么直接等于左斜上的值 。 注意charAt要-1 才是正确的字符串的位置
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = new Node(dp[i - 1][j - 1].val, Node.CHOICE_NOTHING);
                } else {
                    //如果不相同，那么从“增加，删除，替换” 3种策略中选择一种最小距离的，并且操作数加一
                    int min = min(
                            dp[i - 1][j].val,
                            dp[i][j - 1].val,
                            dp[i - 1][j - 1].val
                    );
                    if (min == dp[i - 1][j].val) {
                        dp[i][j] = new Node(min + 1, Node.CHOICE_DELETE);
                    } else if (min == dp[i][j - 1].val) {
                        dp[i][j] = new Node(min + 1, Node.CHOICE_INSERT);
                    } else if (min == dp[i - 1][j - 1].val) {
                        dp[i][j] = new Node(min + 1, Node.CHOICE_CHANGE);
                    }
                }
            }
        }
        //返回最右下角的值
        return dp[s1.length()][s2.length()];
    }

    public void printPath() {
        //存储操作语句
        LinkedList<String> ops = new LinkedList<>();
        for (int i = s1.length(), j = s2.length(); i > 0 || j > 0; ) {
            Node currentNode = dp[i][j];
            if (currentNode.choice == Node.CHOICE_NOTHING) {
                ops.addFirst(format("跳过相同字符：'%s'", s2.charAt(j - 1)));
                i--;
                j--;
                continue;
            } else if (currentNode.choice == Node.CHOICE_INSERT) {
                ops.addFirst(format("插入字符：'%s'", s2.charAt(j - 1)));
                j--;
            } else if (currentNode.choice == Node.CHOICE_DELETE) {
                ops.addFirst(format("删除字符：'%s'", s1.charAt(i - 1)));
                i--;
            } else if (currentNode.choice == Node.CHOICE_CHANGE) {
                ops.addFirst(format("替换字符：'%s'--->’%s‘", s1.charAt(i - 1), s2.charAt(j - 1)));
                i--;
                j--;
            }
        }
        //开始打印
        for (String op : ops) {
            System.out.println(op);
        }
    }

    private String format(String text, Character... param) {
        return String.format(text, param);
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
