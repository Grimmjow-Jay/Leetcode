package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 93. 复原 IP 地址
 *
 * <pre>
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *  
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 *
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 *
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * </pre>
 *
 * @author Grimm
 * @date 2021/3/2
 */
public class RestoreIpAddresses {

    public static void main(String[] args) {
        String s = "25525511135";
        List<String> ipAddresses = restoreIpAddresses(s);
        System.out.println(ipAddresses);
    }

    private static List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        int[] nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            nums[i] = s.charAt(i) - 48;
        }
        dfs(ans, s, nums, new ArrayList<>(), -1);
        return ans;
    }

    private static void dfs(List<String> ans, String s, int[] nums, List<Integer> dotIdx, int idx) {

        if (dotIdx.size() > 3) {
            return;
        }

        if (dotIdx.size() == 3) {
            int a = -1;
            StringJoiner joiner = new StringJoiner(".");
            for (Integer dot : dotIdx) {
                joiner.add(s.substring(a + 1, dot + 1));
                a = dot;
            }
            joiner.add(s.substring(a + 1));
            ans.add(joiner.toString());
            return;
        }

        for (int i = idx + 1; i < s.length() - 1 && i <= idx + 3; i++) {
            dotIdx.add(i);
            if (isValid(dotIdx, i, nums)) {
                dfs(ans, s, nums, dotIdx, i);
            }
            dotIdx.remove((Integer) i);
        }

    }

    private static boolean isValid(List<Integer> dotIdx, int i, int[] nums) {

        if (dotIdx.size() == 1) {
            return isValid(nums, 0, i + 1);
        } else if (dotIdx.size() == 2) {
            return isValid(nums, dotIdx.get(0) + 1, dotIdx.get(1) + 1);
        } else {
            return isValid(nums, dotIdx.get(1) + 1, dotIdx.get(2) + 1)
                    && isValid(nums, dotIdx.get(2) + 1, nums.length);
        }

    }

    private static boolean isValid(int[] nums, int start, int end) {
        if (end - start > 3) {
            return false;
        }
        if (end - start == 1) {
            return true;
        } else if (nums[start] == 0) {
            return false;
        }
        int sum = 0;
        while (start < end) {
            sum = sum * 10 + nums[start];
            start++;
        }
        return sum < 256;

    }
}
