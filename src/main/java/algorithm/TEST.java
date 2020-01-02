package algorithm;

import algorithm.ArrayStack;
import org.junit.Test;

public class TEST {
    @org.junit.Test
    public void test() {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push("1");
        arrayStack.push("2");
        arrayStack.push("3");
        arrayStack.push("4");
        arrayStack.push("5");
        arrayStack.push("6");
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
    }

    @Test
    /**
     *
     MD5(" 今天我来讲哈希算法 ") = bb4767201ad42c74e650c1b6c03d78fa
     MD5("jiajia") = cd611a31ea969b908932d44d126d195b
     */
    public void testQueue(){
        ArrayQueue queue = new ArrayQueue(5);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.print();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.print();
        queue.enqueue("7");
        queue.enqueue("8");
        queue.dequeue();
        queue.dequeue();
        queue.print();
        queue.enqueue("9");
        queue.enqueue("10");
        queue.enqueue("11");
        queue.enqueue("12");
        queue.print();
    }
}
