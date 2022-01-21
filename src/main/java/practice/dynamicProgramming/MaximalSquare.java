package practice.dynamicProgramming;

/**
 * 最大正方形
 * <pre>
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 * </pre>
 */
public class MaximalSquare {
    public static void main(String[] args) {
        char[][] matrix = {
                {'0', '1'}
        };
        int maximalSquare = maximalSquare(matrix);
        System.out.println(maximalSquare);
    }

    private static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;

        int maxSqrtSquare = matrix[0][0] == '0' ? 0 : 1;
        // 矩阵中的值代表该位置的最大正方形的边长
        int[][] sqrtSquare = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    sqrtSquare[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    sqrtSquare[i][j] = matrix[i][j] == '0' ? 0 : 1;
                } else {
                    sqrtSquare[i][j] = Math.min(Math.min(sqrtSquare[i - 1][j], sqrtSquare[i][j - 1]), sqrtSquare[i - 1][j - 1]) + 1;
                }
                maxSqrtSquare = Math.max(maxSqrtSquare, sqrtSquare[i][j]);
            }
        }
        return maxSqrtSquare * maxSqrtSquare;
    }
}
