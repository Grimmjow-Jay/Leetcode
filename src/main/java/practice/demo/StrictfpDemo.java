package practice.demo;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/5/10
 */
public class StrictfpDemo {

    public static void main(String[] args) {
        double a = 0.1;
        double b = 0.2;
        System.out.println(sum1(a, b)); // 0.30000000000000004
        System.out.println(sum2(a, b)); // 0.30000000000000004
        System.out.println(sum3(a, b)); // 0.3
    }

    private static strictfp double sum1(double a, double b) {
        return a + b;
    }

    private static double sum2(double a, double b) {
        return a + b;
    }

    private static double sum3(double a, double b) {
        return BigDecimal.valueOf(a)
                .add(BigDecimal.valueOf(b))
                .doubleValue();
    }

}
