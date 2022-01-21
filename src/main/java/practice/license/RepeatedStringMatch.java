package practice.license;

/**
 * 686. 重复叠加字符串匹配
 *
 * <pre>
 * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 *
 * 示例 1：
 * 输入：a = "abcd", b = "cdabcdab"
 * 输出：3
 * 解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
 *
 * 示例 2：
 * 输入：a = "a", b = "aa"
 * 输出：2
 *
 * 示例 3：
 * 输入：a = "a", b = "a"
 * 输出：1
 *
 * 示例 4：
 * 输入：a = "abc", b = "wxyz"
 * 输出：-1
 *
 * 提示：
 * 1 <= a.length <= 104
 * 1 <= b.length <= 104
 * a 和 b 由小写英文字母组成
 * </pre>
 *
 * @author Jay Yang
 * @date 2021/12/22
 */
public class RepeatedStringMatch {

    public static void main(String[] args) {
        String a = "abcd";
        String b = "cdabcdab";
        int repeatedStringMatch = repeatedStringMatch(a, b);
        System.out.println(repeatedStringMatch);
    }

    private static int repeatedStringMatch(String a, String b) {
        int an = a.length(), bn = b.length();
        int index = strStr(a, b);
        if (index == -1) {
            return -1;
        }
        if (an - index >= bn) {
            return 1;
        }
        return (bn + index - an - 1) / an + 2;
    }

    public static int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        for (int i = 0, j = 0; i - j < n; i++) {
            while (j > 0 && haystack.charAt(i % n) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i % n) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

}
