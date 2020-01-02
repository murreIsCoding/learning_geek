package dynamic_program;

public class Package {

    // weight:物品重量，n:物品个数，w:背包可承载重量
    public static int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1]; // 默认值false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
                if (states[i - 1][j] == true) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; ++j) {//把第i个物品放入背包
                if (states[i - 1][j] == true) {
                    states[i][j + weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n - 1][i] == true) {
                return i;
            }
        }
        return 0;
    }

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j] == true) {
                    states[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) {
                return i;
            }
        }
        return 0;
    }




    public static void main(String[] args) {
        int[] weight = {2, 2, 4, 6, 3};  // 物品重量
        int n = 5; // 物品个数
        int w = 9; // 背包承受的最大重量
        int result = knapsack2(weight, 5, 9);
        System.out.println(result);

    }
}
