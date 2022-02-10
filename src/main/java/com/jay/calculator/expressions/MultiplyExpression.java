package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public class MultiplyExpression extends CalculableExpression {

    private CalculableExpression first;

    private CalculableExpression second;

    public MultiplyExpression(CalculableExpression first, CalculableExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public BigDecimal calculate() {
        return first.calculate()
                .multiply(second.calculate());
    }

    /**
     * @author Jay Yang
     * @date 2022/2/9
     */
    public static class MultiplySymbol extends SymbolExpression {

        @Override
        public OrderGroup order() {
            return OrderGroup.MULTIPLY_DIVIDE;
        }

        @Override
        public String symbol() {
            return "*";
        }

        @Override
        public Expression shrink(Expression head) {

            MultiplyExpression multiplyExpression = new MultiplyExpression(
                    (CalculableExpression) this.prev, (CalculableExpression) this.next);
            join(multiplyExpression, this.next.next);
            join(this.prev.prev, multiplyExpression);

            if (head == this.prev) {
                return multiplyExpression;
            } else {
                return head;
            }
        }
    }
}
