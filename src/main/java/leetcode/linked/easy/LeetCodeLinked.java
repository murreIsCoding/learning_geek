package leetcode.linked.easy;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LeetCodeLinked {
    //Definition for singly-linked list.


    /**
     * 83 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     * <p>
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     */
    public static ListNode removeRepeatedNode(ListNode head) {
        ListNode current = head;
        //空链表
        if (current == null) {
            return current;
        } else {
            while (true) {
                if (current.next == null) {
                    return head;
                } else if (current.next.val > current.val) {
                    current = current.next;
                    continue;
                } else {
                    current.next = current.next.next;
                    continue;
                }
            }

        }
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(9);

        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode4 = new ListNode(9);
        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(9);
        ListNode listNode7 = new ListNode(9);
        ListNode listNode8 = new ListNode(9);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(9);
        ListNode listNode11 = new ListNode(9);


        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        listNode9.next = listNode10;
        listNode10.next = listNode11;
        ListNode result = addTwoNumbers(listNode1, listNode2);
        printResult(result);
    }

    public static void printResult(ListNode result) {
        System.out.print(result.val + "-->");
        while (result.next != null) {
            System.out.print(result.next.val + "-->");
            result = result.next;
        }
        System.out.print("null");
    }

    /**
     * 2两数相加
     * <p>
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode head = listNode;
        for (; l1 != null || l2 != null; ) {
            int item1 = 0;
            int item2 = 0;
            if (l1 != null) {
                item1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                item2 = l2.val;
                l2 = l2.next;
            }
            int sum = item1 + item2 + listNode.val;
            if (sum > 9) {
                sum = sum % 10;
                listNode.next = new ListNode(1);
            } else if (l1 != null || l2 != null) {
                listNode.next = new ListNode(0);
            } else {
                listNode.next = null;
            }
            listNode.val = sum;
            listNode = listNode.next;
        }
        return head;
    }

    private static ListNode numToListNode(long sum) {
        ListNode head = new ListNode((int) (sum % 10));
        ListNode temp = head;
        sum = sum / 10;
        while (sum > 0) {
            int val = (int) (sum % 10);
            sum = sum / 10;
            temp.next = new ListNode(val);
            temp = temp.next;
        }
        temp.next = null;
        return head;

    }

    public static long getSum(ListNode head, int length) {
        long sum = 0;
        for (int i = length - 1; i >= 0; i--) {
            int val = head.val;
            long temp = val;
            for (int j = 0; j < i; j++) {
                temp = temp * 10;
            }
            sum += temp;
            head = head.next;
        }
        return sum;
    }

    /**
     * 反转链表
     *
     * @return
     */
    public static ListNode reverseListNode(ListNode listNode) {
        if (listNode == null) {
            return listNode;
        }
        ListNode pre = null;
        while (listNode != null) {
            ListNode nextNode = listNode.next;
            listNode.next = pre;
            pre = listNode;
            listNode = nextNode;
        }

        return pre;
    }

    /**
     * 计算链表长度
     */
    public static int calListLength(ListNode listNode) {
        int length = 0;
        while (listNode != null) {
            length++;
            listNode = listNode.next;
        }
        return length;
    }

    /**
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     */
    public static ListNode mergeKLists(ListNode... lists) {
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        }); //小顶堆，默认容量为11
        ListNode temp = null;
        ListNode head = null;
        for (ListNode listNode : lists) {
            if (listNode != null) {
                minHeap.add(listNode);
            }
        }
        while (minHeap.size() != 0) {
            ListNode min = minHeap.poll();
            if (min.next != null) {
                minHeap.add(min.next);
            }
            if (temp == null) {
                temp = new ListNode(min.val);
                head = temp;
            } else {
                temp.next = new ListNode(min.val);
                temp = temp.next;
            }
        }
        return head;
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例:
     * 给定
     * 1->2->3->4->5->6
     * 2->1->3->4->5->6
     * 你应该返回 2->1->4->3->6->5.
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode l1 = null;
        ListNode l2 = head;
        if (l2 == null) {
            return head;
        }
        ListNode l3 = l2.next;
        if (l3 == null) {
            return head;
        }
        ListNode result = l3;
        while (l2 != null && l3 != null) {
            ListNode temp = l3.next;
            l3.next = l2;
            if (l1 != null) {
                l1.next = l3;
            }
            l2.next = temp;
            l1 = l2;
            l2 = temp;
            if (l2 == null) {
                return result;
            } else {
                l3 = l2.next;
            }
        }
        return result;
    }

    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;
        return next;
    }

    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     * <p>
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NUL
     * 思路：计算出新的头   计算新的尾   在旧尾接上接上旧头  新的尾接null
     */

    public static ListNode rotateRight(ListNode head, int k) {
        ListNode oldHead = head;
        int length = 1;
        while (head.next != null) {
            length++;
            head = head.next;
        }
        ListNode oldTail = head;
        int newHeadIndex = k % length;
        for (int i=0;i<newHeadIndex-1;i++){

        }
        return null;
    }
}
