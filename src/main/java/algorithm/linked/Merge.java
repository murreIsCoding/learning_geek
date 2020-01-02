package algorithm.linked;

import org.junit.Test;

/**
 * 两个有序链表合并
 */
public class Merge {
    public Node<Integer> genNode(int start ) {
        Node<Integer> head = new Node();
        head.value = 0;
        Node<Integer> temp = head;
        for (int i =0, j=start; i <= 10; i++,j++) {
            temp.next = new Node<>();
            temp.next.value = j;
            temp = temp.next;
        }
        return head;
    }

    @Test
    public void test() {
        Node<Integer> head1 = genNode(2);
        Node<Integer> head2 = genNode(3);
        Node result = mergeLiked(head1,head2);
        Util.printNode(result);
    }

    public Node<Integer> mergeLiked(Node<Integer> node1, Node<Integer> node2) {
        Node<Integer> temp1 = node1;
        Node<Integer> temp2 = node2;
        Node<Integer> head = new Node<>();
        Node<Integer> newTemp = head;
        while (temp1 != null || temp2 != null) {
            if (temp2==null){
                newTemp.value = temp1.value;
                temp1=temp1.next;
            }else if (temp1==null){
                newTemp.value = temp2.value;
                temp2=temp2.next;
            }else if (temp1.value <= temp2.value) {
                newTemp.value = temp1.value;
                temp1=temp1.next;
            } else {
                newTemp.value = temp2.value;
                temp2=temp2.next;
            }
            newTemp.next = new Node<>();
            newTemp = newTemp.next;
        }
        return head;
    }

}
