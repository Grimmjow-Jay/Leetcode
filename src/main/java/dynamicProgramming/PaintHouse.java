package dynamicProgramming;

/**
 * 粉刷房子
 * <pre>
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，
 * 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
 * 每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
 *
 * 例如，costs[0][0]表示第 0 号房子粉刷成红色的成本花费；costs[1][2]表示第1号房子粉
 * 刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
 * 注意：
 * 所有花费均为正整数。
 *
 * 示例：
 * 输入: [[17,2,17],[16,16,5],[14,3,19]]
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 *      最少花费: 2 + 5 + 3 = 10。
 * </pre>
 */
public class PaintHouse {
    public static void main(String[] args) {
        int[][] costs = {
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19}
        };
        int minCost = minCost(costs);
        System.out.println(minCost);
    }

    private static int minCost(int[][] costs) {
        int m = costs.length;
        // 当前题目下n总是等于3，推广到更多颜色
        int n = costs[0].length;

        int[][] minCosts = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    minCosts[0][j] = costs[0][j];
                    continue;
                }
                int lastMin = j == 0 ? minCosts[i - 1][1] : minCosts[i - 1][0];
                for (int k = 0; k < n; k++) {
                    if (k != j) {
                        lastMin = Math.min(lastMin, minCosts[i - 1][k]);
                    }
                }
                minCosts[i][j] = lastMin + costs[i][j];
            }
        }
        int minCost = minCosts[m - 1][0];
        for (int i = 1; i < n; i++) {
            minCost = Math.min(minCost, minCosts[m - 1][i]);
        }
        return minCost;
    }
}
