package algorithm.linked;

public class Util {
    public static void printNode(Node head) {
        StringBuffer sb  = new StringBuffer();
        while (head != null) {
            sb.append(head.value);
            sb.append(",");
            head = head.next;
        }
        System.out.println(sb.toString());
    }
}
