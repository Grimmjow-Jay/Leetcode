package practice.dynamicProgramming;

/**
 * 不同路径2
 * <pre>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * ┌──────┬──────┬──────┐
 * │Start │  0   │  0   │
 * ├──────┼──────┼──────┤
 * │  0   │  1   │  0   │
 * ├──────┼──────┼──────┤
 * │  0   │  0   │Finish│
 * └──────┴──────┴──────┘
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * </pre>
 */
public class UniquePaths2 {
    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int uniquePaths = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(uniquePaths);
    }

    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    matrix[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    matrix[0][0] = 1;
                    continue;
                }
                if (i == 0) {
                    matrix[0][j] = matrix[0][j - 1];
                    continue;
                }
                if (j == 0) {
                    matrix[i][0] = matrix[i - 1][0];
                    continue;
                }
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }

        return matrix[m - 1][n - 1];
    }

}
