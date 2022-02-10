package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public class SubtractExpression extends CalculableExpression {

    private CalculableExpression first;

    private CalculableExpression second;

    public SubtractExpression(CalculableExpression first, CalculableExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public BigDecimal calculate() {
        return first.calculate()
                .subtract(second.calculate());
    }

    /**
     * @author Jay Yang
     * @date 2022/2/9
     */
    public static class SubtractSymbol extends SymbolExpression {

        @Override
        public OrderGroup order() {
            return OrderGroup.ADD_SUBTRACT;
        }

        @Override
        public String symbol() {
            return "-";
        }

        @Override
        public Expression shrink(Expression head) {

            SubtractExpression subtractExpression = new SubtractExpression(
                    (CalculableExpression) this.prev, (CalculableExpression) this.next);
            join(subtractExpression, this.next.next);
            join(this.prev.prev, subtractExpression);

            if (head == this.prev) {
                return subtractExpression;
            } else {
                return head;
            }
        }
    }
}
