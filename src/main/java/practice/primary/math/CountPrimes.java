package practice.primary.math;

/**
 * <pre>
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * </pre>
 */
public class CountPrimes {

    public static void main(String[] args) {
        int n = 10;
        int countPrimes = countPrimes(n);
        System.out.println(countPrimes);
    }

    private static int countPrimes(int n) {
        boolean[] notPrim = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!notPrim[i]) {
                // i 的倍数不可能是素数了
                for (int j = 2 * i; j < n; j += i) {
                    notPrim[j] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrim[i]) count++;
        }
        return count;
    }
}
