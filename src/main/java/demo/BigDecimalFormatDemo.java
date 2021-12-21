package demo;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Jay Yang
 * @date 2021/12/21
 */
public class BigDecimalFormatDemo {

    public static void main(String[] args) {

        System.out.println(new DecimalFormat("#.###").format(new BigDecimal("4.12456542")));

        System.out.println(new DecimalFormat("#.###").format(new BigDecimal("4.12")));

        System.out.println(new DecimalFormat("#.000").format(new BigDecimal("0.12")));

        System.out.println(new DecimalFormat("0.000").format(new BigDecimal("0.12")));

    }

}
