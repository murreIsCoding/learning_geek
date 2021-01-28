package labuladong.dynamic_program;

import java.util.HashMap;
import java.util.Map;

/**
 * 正则表达式 相关题目
 * 给定一个字符串s 和一个字符模式p,支持 . *
 * .匹配任意单个字符
 * *匹配0或多个字符
 * 匹配应该覆盖整个字符串而不是部分字符
 * 输入s="aa" p="a*" 返回true
 */
public class Regex {

    Map<Index, Boolean> memo = new HashMap<>();

    /**
     * 判断2个字符串是否相等
     *
     * @param text
     * @param pattern
     * @return
     */
    boolean twoStringisMatch(String text, String pattern) {
        int i = 0;
        int j = 0;
        while (j < pattern.length()) {
            if (i >= text.length()) {
                return false;
            }
            if (pattern.charAt(j++) != text.charAt(i++)) {
                return false;
            }
        }
        return j == text.length();
    }

    /**
     * 一个字符串从Index 开始是否是匹配的
     */
    boolean twoStringisMatch_recursion(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean first_match = text.charAt(0) == pattern.charAt(0);
        return first_match && twoStringisMatch_recursion(text.substring(1), pattern.substring(1));
    }

    /**
     * 有.的正则匹配
     */
    boolean dotMatch_recursion(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean first_match = !text.isEmpty() && (pattern.charAt(0) == '.' || pattern.charAt(0) == text.charAt(0));
        return first_match && dotMatch_recursion(text.substring(1), pattern.substring(1));
    }

    /**
     * 有.和*的正则匹配
     * * C* 0次或多次出现C
     */
    boolean isMatch_3(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean first_match = !text.isEmpty() && (pattern.charAt(0) == '.' || pattern.charAt(0) == text.charAt(0));
        //"aab" "a*b" 0次或N次
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return isMatch_3(text, pattern.substring(2)) ||
                    first_match && isMatch_3(text.substring(1), pattern);
        }
        return first_match && isMatch_3(text.substring(1), pattern.substring(1));
    }

    /**
     * 最终版本,带
     * 有.和*的正则匹配
     * * C* 0次或多次出现C
     */
    boolean isMatch_3_index(String text, String pattern, int i, int j) {
        Index index = new Index(i, j);
        Boolean result;
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        if (pattern.length() == j) {
            result = (text.length() == i);
        } else {
            boolean first_match = i<text.length() && (pattern.charAt(j) == '.' || pattern.charAt(j) == text.charAt(i));
            if (pattern.length() - j >= 2 && pattern.charAt(j + 1) == '*') {
                result = isMatch_3_index(text, pattern, i, j + 2) ||
                        first_match && isMatch_3_index(text, pattern, i + 1, j);
            } else {
                result = first_match && isMatch_3_index(text, pattern, i + 1, j + 1);
            }
        }
        memo.put(index, result);
        //普通
        return result;
    }

    class Index {
        int index1;
        int index2;

        public Index(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

}
