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

        System.out.println("------");

        compareSpeed();

    }

    private static void compareSpeed() {
        BigDecimal value = new BigDecimal("12345678.9101112");
        int count = 1000000;

        long l1 = System.currentTimeMillis();
        String format1 = null;
        for (int i = 0; i < count; i++) {
            format1 = format1(value);
        }
        long l2 = System.currentTimeMillis();
        String format2 = null;
        for (int i = 0; i < count; i++) {
            format2 = format2(value, 3);
        }
        long l3 = System.currentTimeMillis();

        String format3 = null;
        for (int i = 0; i < count; i++) {
            format3 = format2(value, 4);
        }
        long l4 = System.currentTimeMillis();

        System.out.println(format1 + ", " + (l2 - l1));
        System.out.println(format2 + ", " + (l3 - l2));
        System.out.println(format3 + ", " + (l4 - l3));

    }

    private static String format1(BigDecimal value) {
        return new DecimalFormat("0.000").format(value);
    }

    private static String format2(BigDecimal value, int digits) {
        String s = value.toString();
        int index = s.indexOf('.');

        StringBuilder substring = new StringBuilder(s.substring(0, index) + ".");
        for (int i = 0; i < digits; i++) {
            substring.append(s.charAt(index + i + 1));
        }
        return substring.toString();
    }

}
