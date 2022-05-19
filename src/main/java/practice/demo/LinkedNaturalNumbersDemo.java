package practice.demo;

import java.util.stream.IntStream;

/**
 * <pre>
 *     找出连续自然数中缺失的数
 * </pre>
 *
 * @author Jay Yang
 * @date 2022/5/19
 */
public class LinkedNaturalNumbersDemo {

    public static void main(String[] args) {

        int[] numbers = IntStream.range(1, 30)
                .filter(i -> i != 23)
                .toArray();

        int x = 0;
        for (int i = numbers[0]; i <= numbers[numbers.length - 1]; i++) {
            x ^= i;
        }
        int y = 0;
        for (int number : numbers) {
            y ^= number;
        }
        int c = x ^ y;
        System.out.println(c);

    }

}
