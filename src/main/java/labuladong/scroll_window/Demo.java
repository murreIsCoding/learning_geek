package labuladong.scroll_window;

import java.util.HashMap;
import java.util.Map;

/**
 * 解题模板
 */
public class Demo {
    //模板
    void demo(String t, String s) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        //初始化need
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        //初始化窗口 和 valid
        int left = 0;
        int right = 0;
        int valid = 0;

        //开始窗口滑动(终止条件是:右边滑到底了)
        while (right < s.length()) {
            //c是移入窗口的字符
            char c = s.charAt(right);
            right++;
            //todo 对窗口内的数据进行更新....

            //todo 当满足收缩左边窗口条件时,这里为了编译通过用true表示
            while (true) {
                //d是移出窗口的值
                char d = s.charAt(left);
                left++;
                //todo 对窗口内的数据进行更新....
            }
        }

    }
}
