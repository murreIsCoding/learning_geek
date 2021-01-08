package labuladong.binary_tree;

import leetcode.tree.TreeNode;

public class BinaryTree {


    public static void main(String[] args) {
//        TreeNode root = TreeNode.genTestTree();
//        System.out.println(count(root));

        // 根据给定的数组创建一棵树
        TreeNode root = ConstructTree.constructTree(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        // 将刚刚创建的树打印出来
        TreeOperation.show(root);
        //操作
//        invertTree(root);
//        flatten(root);
        // 将刚刚创建的树打印出来
//        TreeOperation.show(root);
        System.out.println("BSD检验结果" + isValid_BSD(root, null, null));
//        System.out.println("BSD搜索结果" + isInBSD(root, 12));
//        insertIntoBST(root, 10);
//        insertIntoBST(root, 8);
//        insertIntoBST(root, 9);
//        insertIntoBST(root, 11);
//        insertIntoBST(root, 12);
        delete(root, 4);
        TreeOperation.show(root);
        System.out.println("BSD检验结果" + isValid_BSD(root, null, null));
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

    /**
     * 是不是一个合法的二叉搜索树,即左子树所有节点都小于根节点 ,右子树所有节点大于根节点
     *
     * @return
     */
    static boolean isValid_BSD(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;//到底了就return true
        }
        if (min != null && root.val < min) {
            return false;
        }
        if (max != null && root.val > max) {
            return false;
        }
        return isValid_BSD(root.left, min, root.val)
                && isValid_BSD(root.right, root.val, max);
    }

    /**
     * 利用二叉搜索树的特性进行搜索
     *
     * @param root
     * @param target
     * @return
     */
    static boolean isInBSD(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target) {
            return true;
        }
        if (target < root.val) {
            return isInBSD(root.left, target);
        }
        if (target > root.val) {
            return isInBSD(root.right, target);
        }
        return false;
    }

    /**
     * 如果我是空的,那我就返回新节点
     * 如果不是,那就根据val大小把这个东西插入到我的左右节点,并返回自身
     *
     * @param root 节点
     * @param val  值
     * @return 返回根节点
     */
    static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    /**
     * 删除,比较复杂
     *
     * @param root
     * @param val
     * @return
     */
    static TreeNode delete(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            //如果当前就是
            //需要分3种情况处理
            //1.下面没有子节点,直接删除
            //2.下面只有一个子节点,那么直接让该子节点代替当前节点
            //3.如果下面有2个子节点,那么需要找到左子树中最大的节点来替换自己.或者找到右子树中最小的节点来替代自己.然后继续删除
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode minRight = getMin(root.right);
                int temp = root.val;
                root.val = minRight.val;
                minRight.val = temp;
                root.right = delete(root.right, val);
            }
        }
        if (val > root.val) {
            root.right = delete(root.right, val);
        }
        if (val < root.val) {
            root.left = delete(root.left, val);
        }
        return root;
    }

    private static TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
