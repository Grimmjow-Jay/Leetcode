package primary.array;

/**
 * <pre>
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4
 * </pre>
 */
public class SingleNumber {

	public static void main(String[] args) {
		int[] nums = { 4, 1, 2, 1, 2, 6, 6 };
		System.out.println(singleNumber2(nums));
	}

	public static int singleNumber(int[] nums) {
		int length = nums.length;
		if (length == 1) {
			return nums[0];
		}
		java.util.Arrays.sort(nums);
		if (nums[length - 1] != nums[length - 2]) {
			return nums[length - 1];
		}

		for (int i = 1; i < length - 1; i += 2) {
			if (nums[i] != nums[i - 1]) {
				return nums[i - 1];
			}
		}
		return nums[0];
	}

	public static int singleNumber2(int[] nums) {
		int result = 0;
		for (int num : nums) {
			result ^= num;
		}
		return result;
	}
}
