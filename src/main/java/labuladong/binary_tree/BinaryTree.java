package labuladong.binary_tree;

import leetcode.tree.TreeNode;

public class BinaryTree {


    public static void main(String[] args) {
//        TreeNode root = TreeNode.genTestTree();
//        System.out.println(count(root));

        // 根据给定的数组创建一棵树
        TreeNode root = ConstructTree.constructTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        // 将刚刚创建的树打印出来
        TreeOperation.show(root);
        //操作
        invertTree(root);
//        flatten(root);
        // 将刚刚创建的树打印出来
        TreeOperation.show(root);
    }

    /**
     * 计算一棵树有多少个节点
     *
     * @param treeNode
     * @return
     */
    static int count(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return 1 + count(treeNode.left) + count(treeNode.right);
    }

    /**
     * 翻转一棵二叉树
     */
    static void invertTree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;
        invertTree(treeNode.left);
        invertTree(treeNode.right);
    }

    /**
     * 把一个完美二叉树所有同层的节点用指针串起来
     *
     * @param root
     * @return
     */
    static TreeNode connect(TreeNode root) {
        connectTwoNode(root.left, root.right);
        return root;
    }

    /**
     * 串起两个节点
     *
     * @param node1
     * @param node2
     */
    static void connectTwoNode(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        //串起来
        node1.next = node2;
        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }

    // 定义：将以 root 为根的树拉平为链表
    //思路,把左子树变成右子树,然后把原来的右子树接到新左子树上面
    static void flatten(TreeNode root) {
        // base case
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);
        //后序遍历
        TreeNode left = root.left;
        TreeNode right = root.right;

        //把左子树变成右子树
        root.left = null;
        root.right = left;

        //找到原来新的右子树的最后节点
        TreeNode last = root;
        while (last.right != null) {
            last = last.right;
        }
        //把原来的right接上
        last.right = right;
    }
}
