package labuladong.scroll_window;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 567题
 * 判断 s 中是否存在 t 的排列
 * 比如:
 * t:ab
 * s:eidbaoooo
 * 返回:true
 * 由于s中包含ba字符串,为ab的一种排列方式,所以返回true
 *
 * 相当给你一个S和一个T，请问你S中是否存在一个子串，包含T中所有字符且不包含其他字符
 */


public class PermutationInString {
    public static void main(String[] args) {
        System.out.println(checkInclusion("bab", "eidbdaoobbbbaboo"));
    }

    // 判断 s 中是否存在 t 的排列
    static boolean checkInclusion(String t, String s) {
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
            // 对窗口内的数据进行更新....
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) == need.get(c)) {
                    valid++;
                }
            }


            //当窗口大小大于t的大小时收缩
            while (right - left + 1 > t.length()) {
                //如果满足了,直接返回
                if (valid == need.size()) {
                    return true;
                }
                //d是移出窗口的值
                char d = s.charAt(left);
                left++;
                //对窗口内的数据进行更新....
                if (need.containsKey(d)) {
                    //和前面的对称相反
                    if (window.get(d) == need.get(d)) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }
        return false;
    }


}
