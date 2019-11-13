package topInterviewQuesitonsIn2018.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 求众数
 * <pre>
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * </pre>
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        int majorityElement = majorityElement(nums);
        System.out.println(majorityElement);
    }

    private static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int majorityElement = 0;
        int majorityCount = 0;
        for (int num : nums) {
            Integer count = map.merge(num, 1, Integer::sum);
            if (count > majorityCount) {
                majorityCount = count;
                majorityElement = num;
            }
        }
        return majorityElement;
    }

}
