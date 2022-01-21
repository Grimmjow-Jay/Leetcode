package practice.lcof;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * <pre>
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 *
 * 示例1:
 *      输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 *      输出: 6
 *      解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * </pre>
 *
 * @author Grimm
 * @date 2021/2/7
 */
public class LianXuZiShuZuDeZuiDaHe {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int max = maxSubArray(nums);
        System.out.println(max);
    }

    private static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSum = nums[0];
        int lastMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            lastMax = Integer.max(lastMax + nums[i], nums[i]);
            maxSum = Integer.max(maxSum, lastMax);
        }

        return maxSum;
    }

}
