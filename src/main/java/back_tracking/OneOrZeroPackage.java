package back_tracking;

public class OneOrZeroPackage {


    public static int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值

    /**
     * i表示考察到哪个物品了；
     * cw表示当前已经装进去的物品的重量和；
     * items表示每个物品的重量
     * n表示物品个数;
     * w背包重量；
     * 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
     * f(0, 0, a, 10, 100)
     */

    public static void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW && cw < w) {
                maxW = cw;
            }
            return;
        }
        f(i + 1, cw, items, n, w);//当前物品不装进背包
        f(i + 1, cw + items[i], items, n, w); //当前物品装进背包
    }

    public static void main(String[] args) {
        int[] a = {20, 30, 40, 45, 12};
        f(0, 0, a, a.length, 100);
        System.out.println(maxW);
    }
}
