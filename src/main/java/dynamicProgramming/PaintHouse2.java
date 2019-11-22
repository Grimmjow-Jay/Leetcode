package dynamicProgramming;

/**
 * 粉刷房子2
 * <pre>
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，
 * 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
 * 每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；
 * costs[1][2]表示第1号房子粉刷成2号颜色的成本花费，以此类推。
 * 请你计算出粉刷完所有房子最少的花费成本。
 *
 * 注意：
 * 所有花费均为正整数。
 *
 * 示例：
 * 输入: [[1,5,3],[2,9,4]]
 * 输出: 5
 * 解释: 将 0号房子粉刷成0号颜色，1号房子粉刷成2号颜色。最少花费: 1 + 4 = 5;
 *      或者将0号房子粉刷成 2号颜色，1号房子粉刷成0号颜色。最少花费: 3 + 2 = 5.
 * 进阶：
 * 您能否在 O(nk) 的时间复杂度下解决此问题？
 * </pre>
 */
public class PaintHouse2 {
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
        int n = costs[0].length;

        int[][] minCosts = new int[m][n];
        System.arraycopy(costs[0], 0, minCosts[0], 0, n);
        for (int i = 1; i < m; i++) {
            int lastMin = Math.min(costs[i - 1][0], costs[i - 1][1]);
            int lastSecMin = Math.max(costs[i - 1][0], costs[i - 1][1]);
            for (int j = 2; j < n; j++) {
                if (minCosts[i - 1][j] <= lastMin) {
                    lastSecMin = lastMin;
                    lastMin = minCosts[i - 1][j];
                } else if (minCosts[i - 1][j] <= lastSecMin) {
                    lastSecMin = minCosts[i - 1][j];
                }
            }
            for (int j = 0; j < n; j++) {
                minCosts[i][j] = (minCosts[i - 1][j] == lastMin ? lastSecMin : lastMin) + costs[i][j];
            }
        }
        int minCost = minCosts[m - 1][0];
        for (int i = 1; i < n; i++) {
            minCost = Math.min(minCost, minCosts[m - 1][i]);
        }
        return minCost;
    }
}
