package topInterviewQuesitonsIn2018;

import java.util.ArrayList;

/**
 * 基本计算器 II
 * <pre>
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 *
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 *
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 *
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 * </pre>
 */
public class BasicCalculator2 {

    public static void main(String[] args) {
        String s = "42+1";
        int calculate = calculate(s);
        System.out.println(calculate);
    }

    private static int calculate(String expression) {
        ArrayList<Comparable> list = new ArrayList<>();
        int num = 0;
        for (char c : expression.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + (int) c - 48;
            } else {
                list.add(num);
                list.add(c);
                num = 0;
            }
        }
        list.add(num);

        // 先计算乘除法
        Integer p = null;
        int indexP = 0;
        for (int i = 0; i < list.size(); i++) {
            Comparable s = list.get(i);
            if (s == null) {
                continue;
            }
            if (s instanceof Character) {
                Character ch = (Character) s;
                if (ch == '*' || ch == '/') {
                    if (p == null) {
                        indexP = i - 1;
                        p = (Integer) list.get(i - 1);
                    }
                    Integer next = (Integer) list.get(i + 1);
                    p = ch == '*' ? p * next : p / next;
                    list.set(indexP, p);
                    list.set(i, null);
                    list.set(i + 1, null);
                } else {
                    p = null;
                }
            }
        }

        // 再计算加减法
        int sum = 0;
        boolean add = true;
        for (Comparable s : list) {
            if (s == null) {
                continue;
            }
            if (s instanceof Character) {
                add = (Character) s == '+';
            } else {
                sum = add ? sum + (int) s : sum - (int) s;
            }
        }
        return sum;
    }
}
