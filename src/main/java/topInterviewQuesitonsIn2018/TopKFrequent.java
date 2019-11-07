package topInterviewQuesitonsIn2018;

import java.util.*;

/**
 * 前 K 个高频元素
 * <pre>
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 说明：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * </pre>
 */
public class TopKFrequent {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 3;
        List<Integer> topKFrequent = topKFrequent(nums, k);
        System.out.println(topKFrequent);
    }

    private static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> topKFrequent = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(Comparator.comparingInt(Map.Entry::getValue));
        for (int i = 0; i < k; i++) {
            topKFrequent.add(entryList.get(entryList.size() - 1 - i).getKey());
        }
        return topKFrequent;
    }

}
