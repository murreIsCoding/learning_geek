package labuladong.dynamic_program;

/**
 * Dp table 存储额外信息用
 */
public class Node {

    public Node(int val, int choice) {
        this.val = val;
        this.choice = choice;
    }

    // 0 代表啥都不做
    // 1 代表插入
    // 2 代表删除
    // 3 代表替换
    static final int CHOICE_NOTHING = 0;
    static final int CHOICE_INSERT = 1;
    static final int CHOICE_DELETE = 2;
    static final int CHOICE_CHANGE = 3;

    int val;
    int choice;

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", choice=" + choice +
                '}';
    }
}
