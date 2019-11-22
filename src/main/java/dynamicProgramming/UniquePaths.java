package dynamicProgramming;

/**
 * 不同路径
 * <pre>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * ┌──────┬──────┬──────┬──────┬──────┬──────┬──────┐
 * │Start │      │      │      │      │      │      │
 * ├──────┼──────┼──────┼──────┼──────┼──────┼──────┤
 * │      │      │      │      │      │      │      │
 * ├──────┼──────┼──────┼──────┼──────┼──────┼──────┤
 * │      │      │      │      │      │      │Finish│
 * └──────┴──────┴──────┴──────┴──────┴──────┴──────┘
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 * </pre>
 */
public class UniquePaths {
    public static void main(String[] args) {
        int m = 7;
        int n = 3;
        int uniquePaths1 = uniquePaths(m, n);
        System.out.println(uniquePaths1);
        int uniquePaths2 = uniquePaths2(m, n);
        System.out.println(uniquePaths2);
    }

    /**
     * m, n太大时会超时
     */
    private static int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    private static int uniquePaths2(int m, int n) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
                }
            }
        }
        return matrix[m - 1][n - 1];
    }
}
