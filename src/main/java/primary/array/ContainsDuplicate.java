package primary.array;

import java.util.Arrays;

/**
 * <pre>
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 * </pre>
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 4, 6};
        System.out.println(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            for (int j = i + 1; j < length; j++) {
                if (nums[j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
