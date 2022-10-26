package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public class FactorialExpression extends CalculableExpression {

    private final BigDecimal max = new BigDecimal(60);

    private final CalculableExpression bottom;

    public FactorialExpression(CalculableExpression bottom) {
        this.bottom = bottom;
    }

    @Override
    public BigDecimal calculate() {
        BigDecimal calculate = bottom.calculate();

        if (!isNonNegativeInteger(calculate)) {
            // 非负整数
            throw new UnsupportedOperationException("invalid factorial param: " + calculate);
        }

        if (calculate.compareTo(max) > 0) {
            throw new UnsupportedOperationException("invalid factorial param, too big");
        }

        BigDecimal result = BigDecimal.ONE;
        for (BigDecimal i = BigDecimal.ONE; i.compareTo(calculate) <= 0; i = i.add(BigDecimal.ONE)) {
            result = result.multiply(i);
        }

        return result;

    }

    /**
     * 是否 非负整数
     */
    private boolean isNonNegativeInteger(BigDecimal number) {

        try {
            return number.toBigIntegerExact()
                    .compareTo(BigInteger.ZERO) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @author Jay Yang
     * @date 2022/2/9
     */
    public static class FactorialSymbol extends SymbolExpression<FactorialExpression> {

        @Override
        public OrderGroup order() {
            return OrderGroup.TRIGONOMETRIC;
        }

        @Override
        public String symbol() {
            return "!";
        }

        @Override
        public FactorialExpression shrink() {

            FactorialExpression factorialExpression = new FactorialExpression((CalculableExpression) this.prev);
            join(factorialExpression, this.next);
            join(this.prev.prev, factorialExpression);

            return factorialExpression;

        }

    }
}
