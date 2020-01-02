package algorithm.linked;

import org.junit.Test;

/**
 * 删除倒数第n个节点
 */
public class DeleteFromBack {

    @Test
    public void test() {
        Node<Integer> head = new Node();
        head.value = 1;
        Node<Integer> temp = head;
        for (int i = 2; i <= 5; i++) {
            temp.next = new Node<>();
            temp.next.value = i;
            temp = temp.next;
        }
        Util.printNode(head);
        Node result = delete(head,5);
        Util.printNode(result);

    }

    public Node delete(Node head, int n) {

        Node fast = head;
        for (int i = 0; i < n; i++) {
            fast=fast.next;
        }
        Node slow =head;
        while (true){
            if (fast==null){
                head = head.next;
                return head;
            }else if (fast.next!=null){
                fast=fast.next;
                slow=slow.next;
            }else {
                break;
            }
        }
        slow.next=slow.next.next;
        return head;
    }
}
