package arrayAndString;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 *
 * 示例 2:
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * </pre>
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        int m = 5;
        int n = 4;
        int x = 1;
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            int[] ints = new int[n];
            for (int j = 0; j < ints.length; j++) {
                ints[j] = x++;
            }
            matrix[i] = ints;
        }
        List<Integer> spiralOrder = spiralOrder(matrix);
        System.out.println(spiralOrder);
    }

    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiralOrder = new ArrayList<>();
        int m;
        if (matrix == null || (m = matrix.length) == 0) {
            return spiralOrder;
        }
        int n = matrix[0].length;

        int i = 0;
        int j = 0;

        // 1:向右，2：向下，3向左，4向上
        int direction = 1;
        boolean addOne = true;
        int loss = 0;
        boolean nowM = false;
        int x = 0;
        while (x < m * n) {
            int count = nowM ? m - loss : n - loss;
            if (count == 0) {
                return spiralOrder;
            }
            for (int y = 0; y < count; y++) {
                spiralOrder.add(matrix[i][j]);
                if (++x >= m * n) {
                    return spiralOrder;
                }
                if (y == count - 1) {
                    direction = direction == 4 ? 1 : direction + 1;
                }
                if (direction == 1) {
                    j++;
                }
                if (direction == 2) {
                    i++;
                }
                if (direction == 3) {
                    j--;
                }
                if (direction == 4) {
                    i--;
                }

            }
            loss = addOne ? loss + 1 : loss;
            addOne = !addOne;
            nowM = !nowM;
        }
        return spiralOrder;
    }

}
