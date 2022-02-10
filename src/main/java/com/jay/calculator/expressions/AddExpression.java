package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public class AddExpression extends CalculableExpression {

    private CalculableExpression first;

    private CalculableExpression second;

    public AddExpression(CalculableExpression first, CalculableExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public BigDecimal calculate() {
        return first.calculate()
                .add(second.calculate());
    }

    /**
     * @author Jay Yang
     * @date 2022/2/9
     */
    public static class AddSymbol extends SymbolExpression {

        @Override
        public OrderGroup order() {
            return OrderGroup.ADD_SUBTRACT;
        }

        @Override
        public String symbol() {
            return "+";
        }

        @Override
        public Expression shrink(Expression head) {

            AddExpression addExpression = new AddExpression(
                    (CalculableExpression) this.prev, (CalculableExpression) this.next);
            join(addExpression, this.next.next);
            join(this.prev.prev, addExpression);

            if (head == this.prev) {
                return addExpression;
            } else {
                return head;
            }
        }
    }
}
