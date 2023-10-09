package practice.demo;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2023/10/9
 */
public class BigDecimalMultiplyDemo {

    public static void main(String[] args) {
        BigDecimal multiplier = BigDecimal.valueOf(3.14);
        BigDecimal multiplicand = BigDecimal.valueOf(2.33);
        BigDecimal product = multiplier.multiply(multiplicand);
        System.out.println(product);
    }

}
