package dynamicProgramming;

/**
 * 最长上升子序列
 * <pre>
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * </pre>
 */
public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 118};
        int lengthOfLIS = lengthOfLIS(nums);
        System.out.println(lengthOfLIS);
    }

    private static int lengthOfLIS(int[] nums) {
        int result = 0;
        int len = nums.length;
        int[] length = new int[len];
        for (int i = 0; i < len; i++) {
            int longest = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    longest = Math.max(longest, length[j]);
                }
            }
            length[i] = longest + 1;
            result = Math.max(result, length[i]);
        }
        return result;
    }
}
