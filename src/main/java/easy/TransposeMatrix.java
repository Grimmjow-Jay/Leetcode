package easy;

import java.util.Arrays;

/**
 * 867. 转置矩阵
 *
 * <pre>
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 *
 * 示例 1：
 * 输入：matrix =
 * [[1,2,3],
 *  [4,5,6],
 *  [7,8,9]]
 * 输出：
 * [[1,4,7],
 *  [2,5,8],
 *  [3,6,9]]
 *
 * 示例 2：
 * 输入：matrix =
 * [[1,2,3],
 *  [4,5,6]]
 * 输出：
 * [[1,4],
 *  [2,5],
 *  [3,6]]
 * </pre>
 *
 * @author Grimm
 * @date 2021/3/15
 */
public class TransposeMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] transpose = transpose(matrix);
        for (int[] row : transpose) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static int[][] transpose(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0][0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transpose = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }

}
