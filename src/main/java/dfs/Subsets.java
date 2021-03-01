package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 78. 子集
 *
 * <pre>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * </pre>
 *
 * @author Grimm
 * @date 2021/3/1
 */
public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        List<List<Integer>> subsets1 = subsets(nums);
        List<List<Integer>> subsets2 = subsets2(nums);

        subsets1.forEach(System.out::println);
        System.out.println("------");
        subsets2.forEach(System.out::println);

    }

    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, ans, new Stack<>(), 0);
        return ans;
    }

    private static void dfs(int[] nums, List<List<Integer>> ans, Stack<Integer> subset, int idx) {
        ans.add(new ArrayList<>(subset));
        for (int i = idx; i < nums.length; i++) {
            subset.push(nums[i]);
            dfs(nums, ans, subset, i + 1);
            subset.pop();
        }
    }

    private static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        int pow = (int) Math.pow(2, nums.length);
        for (int i = 0; i < pow; i++) {
            int a = i;
            List<Integer> subset = new ArrayList<>();
            for (int j = nums.length - 1; j >= 0; j--) {
                if ((a & 1) == 1) {
                    subset.add(nums[j]);
                }
                a >>= 1;
            }
            ans.add(subset);
        }

        return ans;
    }

}
