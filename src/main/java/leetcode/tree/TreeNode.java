package leetcode.tree;

//Definition for a binary tree node.
public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;

    public TreeNode() {
    }

    public TreeNode(Integer x) {
        val = x;
    }

    public static TreeNode genTestTree() {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        root.left = node1;
        root.right = node2;
//
        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node5.left = node7;
        node5.right = node8;
        return root;
    }
}
