package labuladong.scroll_window;

import java.util.HashMap;
import java.util.Map;

/**
 * 这是 LeetCode 第 3 题
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 * <p>
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */

/**
 * 我的思路
 * 左边收缩时机----当存在重复字符时,左边需要滑动到第一个重复字符,然后右边继续滑动
 * 由于是要求最长,所以应该是在右滑期间更新最终结果
 */
public class LongestSubstringWithoutRepeating {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(""));
    }

    static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();

        //初始化窗口 和 valid
        int left = 0;
        int right = 0;
        int longest = Integer.MIN_VALUE;

        //开始窗口滑动(终止条件是:右边滑到底了)
        while (right < s.length()) {
            //c是移入窗口的字符
            char c = s.charAt(right);
            right++;

            Integer num = window.getOrDefault(c, 0);
            if (num == 0) {
                window.put(c, 1);
                longest = longest < right - left ? right - left : longest;
            } else {
                window.put(c, num + 1);
            }


            //当c的个数始终大于1时,收缩左边
            while (window.get(c) > 1) {
                //d是移出窗口的值
                char d = s.charAt(left);
                left++;
                window.put(d, window.getOrDefault(d, 0) - 1);
            }
        }
        return longest == Integer.MIN_VALUE ? 0 : longest;
    }
}
