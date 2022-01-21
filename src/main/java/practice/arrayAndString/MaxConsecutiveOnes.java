package practice.arrayAndString;

/**
 * 最大连续1的个数
 * <pre>
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 *
 * 输入的数组只包含0和1。
 * 输入数组的长度是正整数，且不超过10,000。
 * </pre>
 */
public class MaxConsecutiveOnes {

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1};
        int maxConsecutiveOnes = findMaxConsecutiveOnes(nums);
        System.out.println(maxConsecutiveOnes);
    }

    private static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int i = -1;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 1) {
                max = Math.max(max, j - i);
            } else {
                i = j;
            }
        }
        return max;
    }

}
