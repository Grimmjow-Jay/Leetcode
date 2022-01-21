package practice.lcof;

/**
 * 剑指 Offer 05. 替换空格
 *
 * <pre>
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 * 0 <= s 的长度 <= 10000
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/7
 */
public class TiHuanKongGe {

    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace(s));
    }

    private static String replaceSpace(String s) {
        StringBuilder builder = new StringBuilder(s.length() * 3);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                builder.append("%20");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

}
