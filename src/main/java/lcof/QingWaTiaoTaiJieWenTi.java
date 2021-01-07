package lcof;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 *
 * <pre>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：21
 *
 * 示例 3：
 * 输入：n = 0
 * 输出：1
 *
 * 提示： 0 <= n <= 100
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/7
 */
public class QingWaTiaoTaiJieWenTi {

    public static void main(String[] args) {
        int n = 100;
        int ways = numWays2(n);
        System.out.println(ways);
        ways = numWays(n);
        System.out.println(ways);
    }

    private static int numWays(int n) {
        return numWays(n, new int[n + 1]);
    }

    private static int numWays(int n, int[] cache) {
        if (cache[n] != 0) {
            return cache[n];
        }
        if (n == 0 || n == 1) {
            return 1;
        }

        return cache[n] = (numWays(n - 1, cache) + numWays(n - 2, cache)) % 1000000007;
    }

    private static int numWays2(int n) {
        int temp = 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result = result + temp;
            temp = result - temp;
            result = result % 1000000007;
        }
        return result;
    }
}
