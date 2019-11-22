package topInterviewQuesitonsIn2018.sortAndSearch;

/**
 * 寻找重复数
 * <pre>
 * 给定一个包含n+1个整数的数组nums，其数字都在1到n之间(包括1和n)，
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 *
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n^2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * </pre>
 */
public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 2};
        int duplicate = findDuplicate(nums);
        System.out.println(duplicate);
    }

    private static int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (num == nums[j]) {
                    return num;
                }
            }
        }
        throw new RuntimeException("No duplicate num.");
    }

}
