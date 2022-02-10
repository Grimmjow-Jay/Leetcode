package com.jay.calculator;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public class RunDemo {

    public static void main(String[] args) {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        BigDecimal result = simpleCalculator.calculate("3!+3.3*12+sin45");
        System.out.println(result);
    }

}
