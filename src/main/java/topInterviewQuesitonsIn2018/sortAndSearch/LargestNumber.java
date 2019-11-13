package topInterviewQuesitonsIn2018.sortAndSearch;

import java.util.Arrays;

/**
 * 最大数
 * <pre>
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 *
 * 示例 1:
 * 输入: [10,2]
 * 输出: 210
 *
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * </pre>
 */
public class LargestNumber {

    public static void main(String[] args) {
        int[] nums = {0, 0};
        String largestNumber = largestNumber(nums);
        System.out.println(largestNumber);
    }

    private static String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strings, (e1, e2) -> (e1 + e2).compareTo(e2 + e1));
        if ("0".equals(strings[0])) {
            return "0";
        }
        return String.join("", strings);
    }

}
