package labuladong.dynamic_program.house_robbery;

import labuladong.binary_tree.ConstructTree;
import leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍问题的关键点在于:从base case 入手,如果一家都不抢那就0,然后从后面往前面推
 */
public class HouseRobbery {
    static int runtime = 0;//执行次数
    static Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
//        int[] home = {2, 3, 2};
        // 根据给定的数组创建一棵树
        TreeNode root = ConstructTree.constructTree(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        System.out.println(rob_binary(root));
//        System.out.println("执行次数:" + runtime);
    }

    /**
     * 一排房子,不能偷相邻的,否则就会报警,求能获利最大的
     * 状态就是打还是不打
     * base case 全都不打 就是 0
     */
    // 主函数
    public static int rob(int[] nums) {
        return dp(nums, 0);
    }

    // 返回 nums[start..] 能抢到的最大值
    private static int dp(int[] nums, int start) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }
        runtime++;
        if (start >= nums.length) {
            return 0;
        }

        int res = Math.max(
                // 不抢，去下家
                dp(nums, start + 1),
                // 抢，去下下家
                nums[start] + dp(nums, start + 2)
        );
        memo.put(start, res);
        return res;
    }

    /**
     * 使用自底向上的解法
     *
     * @param nums
     * @return
     */
    static int rob_in_dp(int[] nums) {
        int n = nums.length;
        // dp[i] = x 表示：
        // 从第 i 间房子开始抢劫，最多能抢到的钱为 x
        // base case: dp[n] = 0
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }

    /**
     * 第一种变形环形房子的打家劫舍的问题
     * 比如[2,3,2] ,应该返回3 而不是4
     *
     * @param nums
     * @return
     */
    static int rob_round(int[] nums) {
        //其实思路很简单,因为收尾不能同时被抢,所以只要比较头不能被抢和尾不能被抢两种情况就行
        int n = nums.length;
        return Math.max(rob_index(nums, 0, n - 2), rob_index(nums, 1, n - 1));
    }

    static int rob_index(int[] nums, int start, int end) {
        int n = nums.length;
        // dp[i] = x 表示：
        // 从第 i 间房子开始抢劫，最多能抢到的钱为 x
        // base case: dp[n] = 0
        int[] dp = new int[n + 2];
        for (int i = end; i >= start; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[start];
    }

    /**
     * 第二种变形 在二叉树上打劫 二叉树上相邻的两个节点不能一起打劫
     * 思路,如果抢了一个节点,那么它的两个子节点就不能抢了,只能抢它的子子节点,如果不抢,那么就能抢它的子节点
     * 再用备忘录优化一下重复解就行了
     */
    public static int rob_binary(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int do_it = root.val + (root.left == null ? 0 : rob_binary(root.left.left) + rob_binary(root.left.right)) +
                (root.right == null ? 0 : rob_binary(root.right.left) + rob_binary(root.right.right));
        int not_do_it = rob_binary(root.left) + rob_binary(root.right);
        return Math.max(do_it, not_do_it);

    }

    int rob_binary_tree(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    /* 返回一个大小为 2 的数组 arr
    arr[0] 表示不抢 root 的话，得到的最大钱数
    arr[1] 表示抢 root 的话，得到的最大钱数 */
    int[] dp(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        // 抢，下家就不能抢了
        int rob = root.val + left[0] + right[0];
        // 不抢，下家可抢可不抢，取决于收益大小
        int not_rob = Math.max(left[0], left[1])
                + Math.max(right[0], right[1]);

        return new int[]{not_rob, rob};
    }

}
