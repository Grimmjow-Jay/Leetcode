package practice.lcof;

/**
 * 剑指 Offer 13. 机器人的运动范围
 *
 * <pre>
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 *
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 *
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/8
 */
public class JiQiRenDeYunDongFanWei {

    public static void main(String[] args) {
        int m = 18;
        int n = 27;
        int k = 13;
        int movingCount = movingCount(m, n, k);
        System.out.println(movingCount);
    }

    private static int movingCount(int m, int n, int k) {
        boolean[][] cache = new boolean[m][n];
        int movingCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canMove(i, j, k, cache)) {
                    movingCount++;
                }
            }
        }
        return movingCount;
    }

    private static boolean canMove(int i, int j, int k, boolean[][] cache) {
        if (i < 0 || j < 0 || i >= cache.length || j >= cache[0].length) {
            return false;
        }
        if (cache[i][j]) {
            return true;
        }
        if (i == 0 && j == 0) {
            cache[i][j] = true;
            return true;
        }

        if (sumDigit(i) + sumDigit(j) > k) {
            return false;
        }

        return cache[i][j] = canMove(i - 1, j, k, cache) || canMove(i, j - 1, k, cache);
    }

    private static int sumDigit(int a) {
        int sum = 0;
        while (a > 0) {
            sum += a % 10;
            a /= 10;
        }
        return sum;
    }


}
