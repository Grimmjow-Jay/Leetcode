package primary.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。
 * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 *
 * 编写一个函数来查找 DNA 分子中所有出现超过一次的 10 个字母长的序列（子串）。
 *
 * 示例：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC", "CCCCCAAAAA"]
 * </pre>
 */
public class RepeatedDnaSequences {
    public static void main(String[] args) {
        String s = "AAAAAAAAAAA";
        List<String> repeatedDnaSequences = findRepeatedDnaSequences(s);
        System.out.println(repeatedDnaSequences);
    }

    private static List<String> findRepeatedDnaSequences(String s) {
        List<String> repeatedDnaSequences = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String substring = s.substring(i, i + 10);
            map.merge(substring, 1, Integer::sum);
        }

        map.forEach((k, v) -> {
            if (v > 1) {
                repeatedDnaSequences.add(k);
            }
        });
        return repeatedDnaSequences;
    }
}
