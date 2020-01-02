package leetcode.tree;


/**
 * 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        } else {
            return hasPathSumDigui(root, sum, 0);
        }
    }

    public boolean hasPathSumDigui(TreeNode treeNode, int sum, int current) {
        current += treeNode.val;
        if (treeNode.left != null) {
            if (hasPathSumDigui(treeNode.left, sum, current)) {
                return true;
            }
        }
        if (treeNode.right != null) {
            if (hasPathSumDigui(treeNode.right, sum, current)) {
                return true;
            }
        }
        if (treeNode.left == null&&treeNode.right == null&&current == sum) {
            return true;
        } else {
            return false;
        }
    }
}
