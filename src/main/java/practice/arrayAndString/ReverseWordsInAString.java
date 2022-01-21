package practice.arrayAndString;

/**
 * <pre>
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *  
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * </pre>
 */
public class ReverseWordsInAString {
    public static void main(String[] args) {
        String s = "the sky is blue";
        String reverseWords = reverseWords(s);
        System.out.println(reverseWords);
    }

    private static String reverseWords(String s) {
        StringBuilder builder = new StringBuilder(s.length());
        int point = s.length();
        boolean b = false;

        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (b) {
                    builder.append(' ').append(chars, i + 1, point - i - 1);
                }
                point = i;
                b = false;
            } else {
                b = true;
            }
        }
        if (b) {
            builder.append(' ').append(chars, 0, point);
        }
        return builder.length() > 0 ? builder.substring(1) : builder.toString();
    }
}
