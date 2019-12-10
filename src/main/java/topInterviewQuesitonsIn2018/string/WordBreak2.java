package topInterviewQuesitonsIn2018.string;

import java.util.*;

/**
 * 单词拆分2
 * <pre>
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
 * 返回所有这些可能的句子。
 *
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * </pre>
 */
public class WordBreak2 {
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> wordBreak = wordBreak(s, wordDict);
        for (String words : wordBreak) {
            System.out.println(words);
        }
    }

    // 该方法提交会超时
    private static List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<String>> listMap = new HashMap<>();
        boolean[] memo = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            List<String> nowList = new ArrayList<>();
            for (String word : wordDict) {
                int j = i - word.length();
                if ((j == -1 || (j >= 0 && memo[j])) && word.equals(s.substring(j + 1, i + 1))) {
                    memo[i] = true;
                    List<String> lastList = listMap.getOrDefault(j, new ArrayList<>());
                    if (lastList.size() != 0) {
                        for (String lastStr : lastList) {
                            nowList.add(lastStr + " " + word);
                        }
                    } else {
                        nowList.add(word);
                    }
                }
            }
            if (!nowList.isEmpty()) {
                listMap.put(i, nowList);
            }
        }
        return memo[s.length() - 1] ? listMap.get(s.length() - 1) : new ArrayList<>();
    }

}
