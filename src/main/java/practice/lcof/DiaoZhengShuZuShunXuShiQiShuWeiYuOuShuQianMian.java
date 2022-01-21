package practice.lcof;

import java.util.Random;
import java.util.StringJoiner;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 *
 * <pre>
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 * 提示：
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/12
 */
public class DiaoZhengShuZuShunXuShiQiShuWeiYuOuShuQianMian {

    public static void main(String[] args) {
        Random random = new Random();
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = random.nextInt(10000);
        }
        int[] exchange = exchange(nums);
        StringJoiner joiner = new StringJoiner(", ");
        for (int i : exchange) {
            joiner.add((i % 2 == 0) + "");
        }
        System.out.println(joiner);
    }

    private static int[] exchange(int[] nums) {
        int mid = 0;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                temp = nums[i];
                nums[i] = nums[mid];
                nums[mid] = temp;
                mid++;
            }
        }
        return nums;
    }

}
