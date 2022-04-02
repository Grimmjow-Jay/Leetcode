package practice.demo;

import java.util.Arrays;

/**
 * @author Jay Yang
 * @date 2022/4/2
 */
public class RemoveDuplicateDemo {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 5, 5, 6,7};
        int i = removeDuplicate(nums);
        System.out.println(i);
        System.out.println(Arrays.toString(nums));
    }

    private static int removeDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }

}
