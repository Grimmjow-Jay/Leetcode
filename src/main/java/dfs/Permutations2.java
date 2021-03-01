package dfs;

import java.util.*;

/**
 * 47. 全排列 II
 *
 * <pre>
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *  
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * </pre>
 *
 * @author Grimm
 * @date 2021/2/26
 */
public class Permutations2 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        List<List<Integer>> permute = permuteUnique(nums);
        permute.forEach(System.out::println);
    }

    private static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, ans, new Stack<>(), new boolean[nums.length], 0);
        return ans;
    }

    private static void dfs(int[] nums, List<List<Integer>> ans, Stack<Integer> permute, boolean[] used, int depth) {

        if (depth == nums.length) {
            ans.add(new ArrayList<>(permute));
        }

        Set<Integer> usedInDep = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!used[i] && !usedInDep.contains(nums[i])) {
                used[i] = true;
                usedInDep.add(nums[i]);
                permute.push(nums[i]);
                dfs(nums, ans, permute, used, depth + 1);
                used[i] = false;
                permute.pop();
            }
        }
    }

}
