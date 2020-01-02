package dynamic_program;

public class PackageValue {

    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[] states = new int[w + 1];
        for (int j = 0; j < w + 1; ++j) {
            states[j] = -1;
        }
        states[0] = 0;
        if (weight[0] <= w) {
            states[weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { //动态规划，状态转移
            for (int j = w - weight[i]; j >= 0; --j) { // 选择第i个物品
                if (states[j] >= 0) {
                    int v = states[j] + value[i];
                    states[j + weight[i]] = v;
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[j] > maxvalue) {
                maxvalue = states[j];
            }
        }
        return maxvalue;
    } 

    public static void main(String[] args) {
        int[] weight ={1,20,50,100};
        int[] value = {50,20,10,5};
        int n = weight.length;
        int w = 30;
        int result = knapsack3(weight,value,n,w);
        System.out.println(result);
    }
}
