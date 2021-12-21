package license;

/**
 * 475. 供暖器
 *
 * <pre>
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 *  
 * 示例 1:
 * 输入: houses = [1,2,3], heaters = [2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 *
 * 示例 2:
 * 输入: houses = [1,2,3,4], heaters = [1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 *
 * 示例 3：
 * 输入：houses = [1,5], heaters = [2]
 * 输出：3
 *
 * 提示：
 * 1 <= houses.length, heaters.length <= 3 * 104
 * 1 <= houses[i], heaters[i] <= 109
 * </pre>
 *
 * @author Jay Yang
 * @date 2021/12/21
 */
public class Heaters {

    public static void main(String[] args) {
        int[] houses = {1, 2, 3, 4};
        int[] heaters = {1, 4};
        int radius = findRadius(houses, heaters);
        System.out.println(radius);
    }

    private static int findRadius(int[] houses, int[] heaters) {
        int max = 0;
        for (int house : houses) {
            max = Math.max(max, minRadius(house, heaters));
        }
        return max;
    }

    private static int minRadius(int house, int[] heaters) {
        int min = Integer.MAX_VALUE;
        for (int heater : heaters) {
            min = Math.min(min, Math.abs(heater - house));
        }
        return min;
    }

}
