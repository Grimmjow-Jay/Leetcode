package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/2/17
 */
public class PowerExpression extends CalculableExpression {

    private CalculableExpression base;
    private CalculableExpression exponent;

    public PowerExpression(CalculableExpression base, CalculableExpression exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public BigDecimal calculate() {
        BigDecimal baseValue = base.calculate();
        BigDecimal exponentValue = exponent.calculate();

        double pow = Math.pow(baseValue.doubleValue(), exponentValue.doubleValue());
        return new BigDecimal(pow);
    }

    /**
     * @author Jay Yang
     * @date 2022/2/17
     */
    public static class PowerSymbol extends SymbolExpression<PowerExpression> {

        @Override
        public OrderGroup order() {
            return OrderGroup.TRIGONOMETRIC;
        }

        @Override
        public String symbol() {
            return "^";
        }

        @Override
        public PowerExpression shrink() {

            PowerExpression powerExpression = new PowerExpression(
                    (CalculableExpression) this.prev, (CalculableExpression) this.next);

            join(powerExpression, this.next.next);
            join(this.prev.prev, powerExpression);

            return powerExpression;

        }

    }
}
