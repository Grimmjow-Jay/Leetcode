package primary.dynamicProgramming;

/**
 * <pre>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * </pre>
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        long start1 = System.nanoTime();
        int ways1 = climbStairs1(40);
        long cost1 = System.nanoTime() - start1;
        System.out.println(ways1);
        System.out.println(cost1);

        long start2 = System.nanoTime();
        int ways2 = climbStairs2(40);
        long cost2 = System.nanoTime() - start2;
        System.out.println(ways2);
        System.out.println(cost2);
    }

    private static int climbStairs1(int n) {
        return climbStairs(n, new int[n + 1]);
    }

    private static int climbStairs(int n, int[] cache) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (cache[n] > 0) {
            return cache[n];
        }
        cache[n] = climbStairs(n - 1, cache) + climbStairs(n - 2, cache);
        return cache[n];
    }

    private static int climbStairs2(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }
}
