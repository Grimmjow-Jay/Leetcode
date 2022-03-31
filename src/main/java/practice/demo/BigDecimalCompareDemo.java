package practice.demo;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/3/31
 */
public class BigDecimalCompareDemo {

    public static void main(String[] args) {

        BigDecimal zero = new BigDecimal("0");
        BigDecimal zeroNegate = new BigDecimal("-0.00");

        int i = zero.compareTo(zeroNegate);
        System.out.println(i);

        System.out.println(zero);
        System.out.println(zeroNegate);

    }

}
