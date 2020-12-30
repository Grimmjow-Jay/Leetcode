package medium;

/**
 * 400. 第N个数字
 * <pre>
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
 * 注意:
 * n 是正数且在32位整数范围内 ( n < 2^31)。
 *
 * 示例 1:
 *  输入: 3
 *  输出: 3
 *
 * 示例 2:
 *  输入: 11
 *  输出: 0
 *
 * 说明: 第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
 * </pre>
 *
 * @author Grimm
 * @date 2020/12/30
 */
public class NthDigit {

    public static void main(String[] args) {
        int n = 1000000000;
        System.out.println(findNthDigit(n));
    }

    private static int findNthDigit(int n) {
        long base = 9;
        long digits = 1;
        while (n - base * digits > 0) {
            n -= base * digits;
            base *= 10;
            digits++;
        }

        // 计算真实代表的数字是多少
        long idx = n % digits;  // 注意由于上面的计算，n现在表示digits位数的第n个数字
        if (idx == 0) {
            idx = digits;
        }
        int number = 1;
        for (int i = 1; i < digits; i++) {
            number *= 10;
        }
        number += (idx == digits) ? n / digits - 1 : n / digits;

        // 从真实的数字中找到我们想要的那个数字
        for (long i = idx; i < digits; i++) {
            number /= 10;
        }
        return number % 10;
    }

}
