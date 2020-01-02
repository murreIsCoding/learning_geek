package algorithm.linked;

import org.junit.Before;
import org.junit.Test;

/**
 * 反转链表
 */
public class Revert {


    Node<String> head = new Node<>();

    @Before
    public void init() {
        head.value = "head";
        Node<String> temp = head;
        for (int i = 0; i < 2; i++) {
            temp.next = new Node<>();
            temp.next.value = i + "";
            temp = temp.next;
        }

    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        Node<String> newHead = revertLinked(head);
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start));
        printNode(newHead);

    }

    public void printNode(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    /**
     * 链表反转 3个指针逐个反转
     *
     * @param head
     * @return
     */
    public Node revertLinked(Node head) {
        //0个或者 1个 节点就不需要了
        if (head == null || head.next == null) {
            return head;
        }
        Node n1 = head;
        Node n2 = head.next;
        Node n3;
        head.next = null;

        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        //第一步，先将头节点
        return n1;
    }

    /**
     * 链表反转 插入法
     *
     * @param head
     * @return
     */
    public Node revertLinked2(Node head) {
        //0个或者 1个 节点就不需要了
        if (head == null || head.next == null) {
            return head;
        }
        Node second = head.next;
        Node temp = head.next.next;
        Node newHead = null;
        Node tempNext;
        while (temp != null) {
            tempNext = temp.next;
            temp.next = head.next;
            head.next = temp;
            newHead = head.next;
            temp = tempNext;
        }
        second.next = head;
        head.next = null;
        return newHead;
    }
}
