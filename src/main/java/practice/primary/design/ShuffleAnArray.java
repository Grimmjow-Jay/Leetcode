package practice.primary.design;

import java.util.Random;

/**
 * <pre>
 * 打乱一个没有重复元素的数组。
 *
 * 示例:
 *
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 *
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 *
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 * </pre>
 */
public class ShuffleAnArray {

    private Random random = new Random();
    private int[] nums;

    private ShuffleAnArray(int[] nums) {
        this.nums = nums;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 8, 9};
        ShuffleAnArray shuffleAnArray = new ShuffleAnArray(nums);
        int[] shuffle = shuffleAnArray.shuffle();
        System.out.println(joinArray(shuffle));

        int[] reset = shuffleAnArray.reset();
        System.out.println(joinArray(reset));
    }

    private static String joinArray(int[] nums) {
        StringBuilder builder = new StringBuilder();
        for (int num : nums) {
            builder.append(num);
        }
        return builder.toString();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    private int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    private int[] shuffle() {
        int len = nums.length;
        int[] res = new int[len];
        System.arraycopy(nums, 0, res, 0, len);
        for (int i = 0; i < len; i++) {
            int temp = res[i];
            int swap = random.nextInt(len);
            res[i] = res[swap];
            res[swap] = temp;
        }
        return res;
    }
}
