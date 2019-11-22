package topInterviewQuesitonsIn2018.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文串
 * <pre>
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * </pre>
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> partition = partition(s);
        partition.forEach(System.out::println);
    }

    private static List<List<String>> partition(String s) {
        List<List<String>> partition = new ArrayList<>();

        return partition;
    }
}

