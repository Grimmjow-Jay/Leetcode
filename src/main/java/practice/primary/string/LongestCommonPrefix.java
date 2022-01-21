package practice.primary.string;

/**
 * <pre>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 * </pre>
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        String longestCommonPrefix = longestCommonPrefix(strs);
        System.out.println(longestCommonPrefix);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0] == null) {
            return "";
        }
        int min = strs[0].length();
        for (String str : strs) {
            min = Math.min(min, str.length());
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < min; i++) {
            char c = strs[0].charAt(i);
            for (String str : strs) {
                if (c != str.charAt(i)) {
                    return builder.toString();
                }
            }
            builder.append(c);
        }
        return builder.toString();
    }

}
