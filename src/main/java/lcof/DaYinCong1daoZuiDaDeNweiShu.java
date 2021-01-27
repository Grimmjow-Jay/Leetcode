package lcof;

import java.util.Arrays;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 *
 * <pre>
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *  
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/16
 */
public class DaYinCong1daoZuiDaDeNweiShu {

    public static void main(String[] args) {
        int n = 1;
        int[] numbers = printNumbers(n);
        System.out.println(Arrays.toString(numbers));

    }

    private static int[] printNumbers(int n) {
        int length = 0;
        int bit = 1;
        for (int i = 1; i <= n; i++) {
            length = 9 * bit + length;
            bit = bit * 10;
        }
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = i + 1;
        }
        return result;
    }

}
