package practice.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 *
 * <pre>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 2: a,b,c
 * 3: d,e,f
 * 4: g,h,i
 * 5: j,k,l
 * 6: m,n,o
 * 7: p,q,r,s
 * 8: t,u,v
 * 9: w,x,y,z
 *
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * </pre>
 *
 * @author Grimm
 * @date 2021/4/10
 */
public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        String digits = "";
        List<String> combinations = letterCombinations(digits);
        System.out.println(combinations);
    }

    private static List<String> letterCombinations(String digits) {
        Character[][] numMap = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };

        List<String> combination = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            char digit = digits.charAt(i);
            Character[] characters = numMap[digit - 48 - 2];
            List<String> next = new ArrayList<>();
            if (combination.isEmpty()) {
                for (Character c : characters) {
                    next.add(c.toString());
                }
            } else {
                for (String builder : combination) {
                    for (Character c : characters) {
                        next.add(builder + c);
                    }
                }
            }
            combination = next;
        }

        return combination;
    }

}
