package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

/**
 * @author Jay Yang
 * @date 2022/2/16
 */
public abstract class BracketSymbolExpression extends SymbolExpression<CalculableExpression> {

    @Override
    public OrderGroup order() {
        return OrderGroup.BRACKET;
    }

    public Expression prev() {
        return super.prev;
    }

    @Override
    public CalculableExpression shrink() {
        throw new UnsupportedOperationException();
    }

    public static class Left extends BracketSymbolExpression {

        @Override
        public String symbol() {
            return "(";
        }

    }

    public static class Right extends BracketSymbolExpression {

        @Override
        public String symbol() {
            return ")";
        }

    }

}
