package labuladong.BFS;

import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最小高度
 */
public class MinDepth {
    public static void main(String[] args) {
        TreeNode root = TreeNode.genTestTree();

        MinDepth minDepth = new MinDepth();
        System.out.println(minDepth.minDepth(root));

    }

    int minDepth(TreeNode root) {
        int step = 0;
        if (root == null) {
            return 0;
        }
        //定义队列
        Queue<TreeNode> queue = new LinkedList<>();
        //把起点放进队列中
        queue.offer(root);
        //开始处理队列的内容
        while (!queue.isEmpty()) {
            //将当前节点向四周扩散(对于二叉树来说就是向叶子节点扩散)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //取出当前节点
                TreeNode cur = queue.poll();

                TreeNode left = cur.left;
                TreeNode right = cur.right;
                //判断是否到达终点
                if (left == null && right == null) {
                    return step;
                }
                //把左右加入队列
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            }
            //步数+1
            step++;
        }
        return step;
    }
}
