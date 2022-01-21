package practice.license;

/**
 * 709. 转换成小写字母
 *
 * <pre>
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 *
 * 示例 1：
 * 输入：s = "Hello"
 * 输出："hello"
 *
 * 示例 2：
 * 输入：s = "here"
 * 输出："here"
 *
 * 示例 3：
 * 输入：s = "LOVELY"
 * 输出："lovely"
 *  
 * 提示：
 * 1 <= s.length <= 100
 * s 由 ASCII 字符集中的可打印字符组成
 * </pre>
 *
 * @author Jay Yang
 * @date 2021/12/23
 */
public class ToLowerCase {

    public static void main(String[] args) {
        String s = "lovely";
        String lowerCase = toLowerCase(s);
        System.out.println(lowerCase);
        int count = 1000000;

        String lowerCase1 = "";
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            lowerCase1 = toLowerCase(s);
        }
        long l2 = System.currentTimeMillis();

        String lowerCase2 = "";
        long l3 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            lowerCase2 = s.toLowerCase();
        }
        long l4 = System.currentTimeMillis();

        System.out.println(lowerCase1 + ": " + (l2 - l1));
        System.out.println(lowerCase2 + ": " + (l4 - l3));

    }

    private static String toLowerCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0, length = chars.length; i < length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] |= 32;
            }
        }
        return new String(chars);
    }

}
