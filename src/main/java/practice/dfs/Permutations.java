package practice.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 46. 全排列
 *
 * <pre>
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * </pre>
 *
 * @author Grimm
 * @date 2021/2/26
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1};
        List<List<Integer>> permute = permute(nums);
        permute.forEach(System.out::println);
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, ans, new Stack<>(), new boolean[nums.length], 0);
        return ans;
    }

    private static void dfs(int[] nums, List<List<Integer>> ans, Stack<Integer> permute, boolean[] used, int depth) {
        if (nums.length == depth) {
            ans.add(new ArrayList<>(permute));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                permute.push(nums[i]);
                used[i] = true;
                dfs(nums, ans, permute, used,depth + 1);
                permute.pop();
                used[i] = false;
            }
        }

    }

}
