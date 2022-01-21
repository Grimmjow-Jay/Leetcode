package practice.primary.array;

/**
 * <pre>
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * </pre>
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        long start = System.nanoTime();
        int maxProduct = maxProduct(nums);
        long end = System.nanoTime();
        System.out.println("cost: " + (end - start) + " nanoseconds");
        System.out.println(maxProduct);
    }

    private static int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int lastMax = 0;
        int lastMin = 0;
        int max = lastMax;
        for (int num : nums) {
            int a = lastMax * num;
            int b = lastMin * num;
            int nowMax = Math.max(num, Math.max(a, b));
            int nowMin = Math.min(num, Math.min(a, b));
            max = Math.max(max, nowMax);
            lastMax = nowMax;
            lastMin = nowMin;
        }
        return max;
    }
}
