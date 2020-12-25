package labuladong.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * leetcode 752题
 */
public class OpenLock {
    public static void main(String[] args) {
        OpenLock openLock = new OpenLock();
        String[] deadends = {"0201", "0101", "0102", "1212", "2002",};
        String target = "0202";
        int step = openLock.openLock(deadends, target);
        System.out.println(step);
    }

    /**
     * @param deadends 死亡密码
     * @param target   目标密码
     * @return
     */
    int openLock(String[] deadends, String target) {
        int step = 0;
        // 核心数据结构:队列
        Queue<String> queue = new LinkedList<>();
        //避免走回头路
        Set<String> visited = new HashSet<>();
        String start = "0000";
        //把起点加入队列
        queue.add(start);
        //把起点 和 死亡密码也加入回头路中
        visited.add(start);
        for (String deadend : deadends) {
            visited.add(deadend);
        }
        //开始处理队列
        while (!queue.isEmpty()) {
            int n = queue.size();
            //把周围节点加到队列中来(对于这里,就是把4位数分别往上或者往下拨一下,也就是8种情况)
            for (int i = 0; i < n; i++) {
                String cur = queue.poll();
                //先判断是否到达终止条件,就是密码是否正确
                if (cur.equals(target)) {
                    return step;
                }
                //4位分别往上拨或者往下拨
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            //增加步数
            step++;
        }
        //如果到这里还没有返回,说明根本就不可能
        return -1;
    }

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }

    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

}
