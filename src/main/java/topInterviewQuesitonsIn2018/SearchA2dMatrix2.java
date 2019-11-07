package topInterviewQuesitonsIn2018;

/**
 * 搜索二维矩阵 II
 * <pre>
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * </pre>
 */
public class SearchA2dMatrix2 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;
        boolean contains = searchMatrix(matrix, target);
        System.out.println(contains);
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        int m;
        if (matrix == null || (m = matrix.length) == 0) {
            return false;
        }
        int n = matrix[0].length;
        int i = m - 1;
        int j = 0;
        while (j < n && i > -1) {
            int value = matrix[i][j];
            if (value == target) {
                return true;
            } else if (value < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }
}
