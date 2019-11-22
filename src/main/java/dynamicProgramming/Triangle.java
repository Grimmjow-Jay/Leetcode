package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 三角形最小路径和
 * <pre>
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * </pre>
 */
public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        int minimumTotal = minimumTotal(triangle);
        System.out.println(minimumTotal);
    }

    private static int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[][] minimumTotals = new int[rows][];
        for (int i = 0; i < rows; i++) {
            List<Integer> row = triangle.get(i);
            int cols = row.size();
            minimumTotals[i] = new int[cols];
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    minimumTotals[i][j] = row.get(j);
                    continue;
                }
                if (j == 0) {
                    minimumTotals[i][j] = minimumTotals[i - 1][j] + row.get(j);
                    continue;
                }
                if (j == i) {
                    minimumTotals[i][j] = minimumTotals[i - 1][j - 1] + row.get(j);
                    continue;
                }
                minimumTotals[i][j] = Math.min(minimumTotals[i - 1][j - 1], minimumTotals[i - 1][j]) + row.get(j);
            }
        }
        int min = minimumTotals[rows - 1][0];
        for (int i = 1, len = minimumTotals[rows - 1].length; i < len; i++) {
            min = Math.min(min, minimumTotals[rows - 1][i]);
        }
        return min;
    }

}
