package practice.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 39. 组合总和
 *
 * <pre>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * </pre>
 *
 * @author Grimm
 * @date 2021/2/26
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7, 4};
        int target = 32;
        List<List<Integer>> combination = combinationSum(candidates, target);
        combination.forEach(System.out::println);
    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, ans, new Stack<>(), 0, 0);
        return ans;
    }

    private static void dfs(int[] candidates, int target, List<List<Integer>> ans,
                            Stack<Integer> combine, int depth, int start) {

        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            target -= candidate;
            combine.push(candidate);
            dfs(candidates, target, ans, combine, depth + 1, i);
            target += candidate;
            combine.pop();
        }

    }

}
