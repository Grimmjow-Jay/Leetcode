package primary.dynamicProgramming;

/**
 * <pre>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * </pre>
 */
public class MaximumSubArray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSubArray1 = maxSubArray(nums);
        System.out.println(maxSubArray1);
        int maxSubArray2 = maxSubArray2(nums);
        System.out.println(maxSubArray2);
    }

    private static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int temp = 0;
        for (int num : nums) {
            if (temp > 0) {
                temp += num;
            } else {
                temp = num;
            }
            maxSum = maxSum > temp ? maxSum : temp;
        }
        return maxSum;
    }

    private static int maxSubArray2(int[] nums) {
        int length = nums.length;
        int maxSum = Integer.MIN_VALUE;
        Integer[][] cache = new Integer[length][length];
        for (int i = 0; i < length; i++) {
            cache[i][i] = nums[i];
            for (int j = i; j < length; j++) {
                Integer sum = cache[i][j];
                if (sum == null) {
                    cache[i][j] = cache[i][j - 1] + nums[j];
                    sum = cache[i][j];
                }
                maxSum = maxSum <= sum ? sum : maxSum;
            }
        }
        return maxSum;
    }
}
