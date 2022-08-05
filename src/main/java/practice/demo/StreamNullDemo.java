package practice.demo;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Jay Yang
 * @date 2022/8/5
 */
public class StreamNullDemo {

    public static void main(String[] args) {

        BigDecimal amount1 = BigDecimal.valueOf(100L);
        BigDecimal amount2 = null;

        long sum = Stream.of(amount1, amount2)
                .filter(Objects::nonNull)
                .mapToLong(BigDecimal::longValue)
                .sum();

        System.out.println(sum);

    }

}
