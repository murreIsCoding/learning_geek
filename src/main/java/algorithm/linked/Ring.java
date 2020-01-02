package algorithm.linked;

import org.junit.Test;

public class Ring {


    @Test
    public void finalTest() {
        int length = 10;
        for (int ringIndex = 0; ringIndex < length; ringIndex++) {

            System.out.println("环位置"+ringIndex);
            Node node = genRingLinked(length, ringIndex);
//            printNode(node);
            Node ringNode = getMeetRing(node);
            System.out.println("相遇位置"+ ringNode.value);
            System.out.println();
        }
    }

    /**
     * 找出环的入口
     * @param head
     * @return
     */
    public Node ringIn(Node head){
        Node meetNode = getMeetRing(head);
        Node n1=head;
        Node n2=meetNode;
        while (n1!=n2){
            n1=n1.next;
            n2=n2.next;
        }
        System.out.println();
        return n1;
    }

    @Test
    public void test (){
        Node head = genRingLinked(100,50);
        Node in = ringIn(head);
        System.out.println("环入口"+in.value);
    }

    public Node genRingLinked(int length, int ringIndex) {
        Node head = new Node();
        head.value = "head";
        Node<String> temp = head;
        Node middleNode = null;
        for (int i = 0; i < length; i++) {
            temp.next = new Node<>();
            if (i == ringIndex) {
                middleNode = temp.next;
            }
            temp.next.value = i + "";
            temp = temp.next;
        }
        temp.value = length+"";
        temp.next = middleNode;
        temp.next.value="meeting!";
        return head;
    }


    public void printNode(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    /**
     * 找出环相遇点
     * @param head
     * @return
     */
    public Node getMeetRing(Node head) {
        int i = 0;
        int j = 0;
        try {
            Node slow = head;
            Node fast = head;

            while (fast.next != null && fast.next.next != null) {
                i++;
                slow = slow.next;
                j++;
                j++;
                fast = fast.next.next;
                if (slow == fast) {
                    return slow;
                }
            }
            return null;
        } finally {
            System.out.println("慢指针行走了" + i + "次");
            System.out.println("块指针行走了" + j + "次");
        }
    }


}
