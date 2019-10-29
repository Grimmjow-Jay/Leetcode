package primary.math;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 *
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 *
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 *
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 *
 * 示例：
 *
 * n = 15,
 *
 * 返回:
 * ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8","Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"]
 * </pre>
 */
public class FizzBuzz {

    public static void main(String[] args) {
        int n = 15;
        List<String> fizzBuzz = fizzBuzz(n);
        System.out.println(fizzBuzz);
    }

    private static List<String> fizzBuzz(int n) {
        List<String> fizzBuzz = new ArrayList<>();
        int i3 = 0;
        int i5 = 0;
        for (int i = 1; i <= n; i++) {
            i3++;
            i5++;
            if (i3 == 3) {
                if (i5 == 5) {
                    fizzBuzz.add("FizzBuzz");
                    i5 = 0;
                } else {
                    fizzBuzz.add("Fizz");
                }
                i3 = 0;
            } else if (i5 == 5) {
                fizzBuzz.add("Buzz");
                i5 = 0;
            } else {
                fizzBuzz.add("" + i);
            }
        }
        return fizzBuzz;
    }

}
