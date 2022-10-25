package practice.demo;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/10/25
 */
public class TaxRateTestDemo {

    public static void main(String[] args) {

        Integer taxRate = 12;
        BigDecimal percentTaxRate = percentTaxRate(taxRate);
        System.out.println(percentTaxRate);

    }

    private static BigDecimal percentTaxRate(Integer taxRate) {
        return taxRate == null ? null : BigDecimal.valueOf(taxRate.doubleValue() / 100);
    }

}
