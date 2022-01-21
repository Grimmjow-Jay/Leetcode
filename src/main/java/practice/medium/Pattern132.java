package practice.medium;

/**
 * 456. 132模式
 *
 * <pre>
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
 * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 * 注意：n 的值小于15000。
 *
 * 示例1:
 * 输入: [1, 2, 3, 4]
 * 输出: False
 * 解释: 序列中不存在132模式的子序列。
 *
 * 示例 2:
 * 输入: [3, 1, 4, 2]
 * 输出: True
 * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 *
 * 示例 3:
 * 输入: [-1, 3, 2, 0]
 * 输出: True
 * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 * </pre>
 *
 * @author Grimm
 * @date 2021/3/24
 */
public class Pattern132 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        boolean exist132Pattern = find132pattern(nums);
        System.out.println(exist132Pattern);
    }

    private static boolean find132pattern(int[] nums) {

        if (nums == null || nums.length < 3) {
            return false;
        }

        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Integer.min(min, nums[i - 1]);
            if (min < nums[i] - 1) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] > min && nums[j] < nums[i]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
