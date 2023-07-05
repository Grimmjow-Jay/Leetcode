package practice.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jay Yang
 * @date 2023/7/5
 */
public class DoubleSumDemo {

    public static void main(String[] args) {

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int size = random.nextInt(10) + 3;
            List<BigDecimal> values = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                BigDecimal value = BigDecimal.valueOf(random.nextInt(500))
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                values.add(value);
            }

            double sum = values.stream()
                    .mapToDouble(BigDecimal::doubleValue)
                    .sum();

            BigDecimal doubleSum = BigDecimal.valueOf(sum);

            BigDecimal bigDecimalSum = values.stream()
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (doubleSum.compareTo(bigDecimalSum) != 0) {
                Thread.dumpStack();
            }

        }

    }

}
