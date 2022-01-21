package practice.arrayAndString;

/**
 * <pre>
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * </pre>
 */
public class AddBinary {

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        String addBinary = addBinary(a, b);
        System.out.println(addBinary);
    }

    private static String addBinary(String a, String b) {
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        char[] bigArray = charsA.length >= charsB.length ? charsA : charsB;
        char[] smallArray = bigArray == charsA ? charsB : charsA;
        boolean plusOne = false;
        int diff = bigArray.length - smallArray.length;
        for (int i = bigArray.length - 1; i >= 0; i--) {
            char x = bigArray[i];
            int index = i - diff;
            char y = index >= 0 ? smallArray[index] : '0';
            bigArray[i] = plusOne ? (x == y ? '1' : '0') : (x == y ? '0' : '1');
            plusOne = plusOne ? !(x == y && x == '0') : (x == y && x == '1');
        }

        return plusOne ? "1" + new String(bigArray) : new String(bigArray);
    }

}
