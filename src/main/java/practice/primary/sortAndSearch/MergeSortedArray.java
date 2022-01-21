package practice.primary.sortAndSearch;

/**
 * <pre>
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 * </pre>
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);
        for (int num : nums1) {
            System.out.print("" + num);
        }
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);

        int x = 0;
        int y = 0;
        int i = 0;
        while (x < m && y < n) {
            int num1 = nums1Copy[x];
            int num2 = nums2[y];
            if (num1 < num2) {
                nums1[i] = num1;
                x++;
            } else {
                nums1[i] = num2;
                y++;
            }
            i++;
        }

        if (x < m) {
            System.arraycopy(nums1Copy, x, nums1, i, m - x);
        }
        if (y < n) {
            System.arraycopy(nums2, y, nums1, i, n - y);
        }
    }
}
