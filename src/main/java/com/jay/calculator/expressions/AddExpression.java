package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public class AddExpression extends CalculableExpression {

    private final CalculableExpression first;

    private final CalculableExpression second;

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
    public static class AddSymbol extends SymbolExpression<AddExpression> {

        @Override
        public OrderGroup order() {
            return OrderGroup.ADD_SUBTRACT;
        }

        @Override
        public String symbol() {
            return "+";
        }

        @Override
        public AddExpression shrink() {

            AddExpression addExpression = new AddExpression(
                    (CalculableExpression) this.prev, (CalculableExpression) this.next);
            join(addExpression, this.next.next);
            join(this.prev.prev, addExpression);

            return addExpression;
        }
    }
}
