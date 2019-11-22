package dynamicProgramming;

/**
 * 最小路径和
 * <pre>
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * </pre>
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int minPathSum = minPathSum(grid);
        System.out.println(minPathSum);
    }

    private static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    matrix[0][0] = grid[0][0];
                    continue;
                }
                if (i == 0) {
                    matrix[0][j] = matrix[0][j - 1] + grid[0][j];
                    continue;
                }
                if (j == 0) {
                    matrix[i][0] = matrix[i - 1][0] + grid[i][0];
                    continue;
                }
                matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][j - 1]) + grid[i][j];
            }
        }
        return matrix[m - 1][n - 1];
    }

}
