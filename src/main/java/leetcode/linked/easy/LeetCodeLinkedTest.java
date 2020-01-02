package leetcode.linked.easy;

import org.junit.Test;

public class LeetCodeLinkedTest {
    /**
     * * [
     * *   1->4->5,
     * *   1->3->4,
     * *   2->6
     * * ]
     * * 输出: 1->1->2->3->4->4->5->6
     */
    @Test
    public void mergeKLists_test() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(5);

        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(4);

        ListNode listNode7 = new ListNode(2);
        ListNode listNode8 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        listNode4.next = listNode5;
        listNode5.next = listNode6;

        listNode7.next = listNode8;


        ListNode listNode = LeetCodeLinked.mergeKLists(listNode1, listNode4, listNode7);
        LeetCodeLinked.printResult(listNode);
    }

    @Test
    public void swapPairs_test() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;


        ListNode listNode = LeetCodeLinked.swapPairs2(listNode1);
        LeetCodeLinked.printResult(listNode);
    }

    @Test
    public void swapPairs_rotate() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;


        ListNode listNode = LeetCodeLinked.rotateRight(listNode1,2);
        LeetCodeLinked.printResult(listNode);
    }
}
