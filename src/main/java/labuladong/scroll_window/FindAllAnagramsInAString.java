package labuladong.scroll_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是 LeetCode 第 438 题，Find All Anagrams in a String，难度 Medium：
 * <p>
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * 说明：
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * <p>
 *  示例 2:
 * 输入:
 * s: "abab" p: "ab"
 * 输出:
 * [0, 1, 2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }

    static List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new ArrayList<>();
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
                //收缩时来判定，如果满足了,直接返回
                if (valid == need.size()) {
                    result.add(left);
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
        return result;
    }
}
