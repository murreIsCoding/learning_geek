package algorithm;

import java.util.List;

public class Node {
    final String conten;
    Integer num;
    final List<Node> childNodes;

    Node maxNode;

    public Node(String conten, List<Node> childNodes) {
        this.conten = conten;
        this.childNodes = childNodes;
    }

    public Node getMaxNode() {
        if (childNodes == null && childNodes.size() == 0) {
            return maxNode;
        }
        maxNode = this;
        for (Node node : childNodes) {
            if (node.num > maxNode.num) {
                maxNode = node;
                node.getMaxNode();
            }
        }
        return maxNode;
    }
}
