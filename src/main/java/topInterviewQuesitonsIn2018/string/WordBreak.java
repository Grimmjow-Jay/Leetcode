package topInterviewQuesitonsIn2018.string;

import java.util.Arrays;
import java.util.List;

/**
 * 单词拆分
 * <pre>
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * </pre>
 */
public class WordBreak {
    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        boolean wordBreak = wordBreak(s, wordDict);
        System.out.println(wordBreak);
    }


    private static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] memo = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (String word : wordDict) {
                int j = i - word.length();
                if (j == -1 || (j >= 0 && memo[j])) {
                    String substring = s.substring(j + 1, i + 1);
                    if (word.equals(substring)) {
                        memo[i] = true;
                        break;
                    }
                }
            }
        }
        return memo[s.length() - 1];
    }

}
