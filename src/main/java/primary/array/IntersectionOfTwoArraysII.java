package primary.array;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
给定两个数组，编写一个函数来计算它们的交集。

示例 1:
输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]

示例 2:
输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。

进阶:

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * </pre>
 */
public class IntersectionOfTwoArraysII {

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 2, 1 };
		int[] nums2 = { 2, 2 };
		int[] intersect = intersect(nums1, nums2);
		for (int i : intersect) {
			System.out.print(i + " ");
		}
	}

	public static int[] intersect(int[] nums1, int[] nums2) {
		List<Integer> list1 = new LinkedList<>();
		for (int num : nums1) {
			list1.add(num);
		}

		List<Integer> list2 = new LinkedList<>();
		for (int num : nums2) {
			list2.add(num);
		}

		List<Integer> result = new LinkedList<>();
		for (int num : list1) {
			Iterator<Integer> iterator = list2.iterator();
			while (iterator.hasNext()) {
				int integer = iterator.next();
				if (integer == num) {
					result.add(num);
					iterator.remove();
					break;
				}
			}
		}
		int[] re = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			re[i] = result.get(i);
		}
		return re;
	}
}
