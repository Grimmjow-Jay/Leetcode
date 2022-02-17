package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/2/17
 */
public class CosExpression extends CalculableExpression {

    private CalculableExpression angle;

    public CosExpression(CalculableExpression angle) {
        this.angle = angle;
    }

    @Override
    public BigDecimal calculate() {
        return BigDecimal.valueOf(Math.cos(angle.calculate().doubleValue()));
    }

    /**
     * @author Jay Yang
     * @date 2022/2/17
     */
    public static class CosSymbol extends SymbolExpression<CosExpression> {

        @Override
        public OrderGroup order() {
            return OrderGroup.TRIGONOMETRIC;
        }

        @Override
        public String symbol() {
            return "cos";
        }

        @Override
        public CosExpression shrink() {

            CosExpression cos = new CosExpression((CalculableExpression) this.next);
            join(cos, this.next.next);
            join(this.prev, cos);

            return cos;

        }

    }
}
