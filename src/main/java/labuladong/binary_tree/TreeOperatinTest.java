package labuladong.binary_tree;

import leetcode.tree.TreeNode;

// // TreeOperationTest.java
public class TreeOperatinTest {
    public static void main(String[] args) {
        // 根据给定的数组创建一棵树
        TreeNode root = ConstructTree.constructTree(new Integer[] {1, 2, 3, 4, 5 ,6, 7});
        // 将刚刚创建的树打印出来
        TreeOperation.show(root);
    }
}
