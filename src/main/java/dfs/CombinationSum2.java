package dfs;

import java.util.*;

/**
 * 40. 组合总和 II
 *
 * <pre>
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * </pre>
 *
 * @author Grimm
 * @date 2021/2/26
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> combination = combinationSum(candidates, target);
        combination.forEach(System.out::println);
    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        Map<Integer, Integer> usedCount = new HashMap<>();
        dfs(candidates, target, ans, new Stack<>(), new boolean[candidates.length], usedCount, 0);
        return ans;
    }

    private static void dfs(int[] candidates, int target, List<List<Integer>> ans,
                            Stack<Integer> combine, boolean[] used, Map<Integer, Integer> usedCount, int depth) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }

        for (int i = 0; i < candidates.length; i++) {
            if (!used[i]) {
                used[i] = true;
                target -= candidates[i];
                combine.push(candidates[i]);
                dfs(candidates, target, ans, combine, used, usedCount, depth + 1);
                target += candidates[i];
                combine.pop();
                used[i] = false;
            }
        }

    }

}
