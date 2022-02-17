package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public class SinExpression extends CalculableExpression {

    private CalculableExpression angle;

    public SinExpression(CalculableExpression angle) {
        this.angle = angle;
    }

    @Override
    public BigDecimal calculate() {
        double sin = Math.sin(angle.calculate().doubleValue());
        return BigDecimal.valueOf(sin);
    }

    /**
     * @author Jay Yang
     * @date 2022/2/9
     */
    public static class SinSymbol extends SymbolExpression<SinExpression> {

        @Override
        public OrderGroup order() {
            return OrderGroup.TRIGONOMETRIC;
        }

        @Override
        public String symbol() {
            return "sin";
        }

        @Override
        public SinExpression shrink() {

            SinExpression sinExpression = new SinExpression((CalculableExpression) this.next);
            join(sinExpression, this.next.next);
            join(this.prev, sinExpression);

            return sinExpression;

        }

    }
}
