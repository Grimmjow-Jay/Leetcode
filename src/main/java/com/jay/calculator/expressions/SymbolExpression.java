package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public abstract class SymbolExpression<T extends CalculableExpression> extends Expression implements Cloneable {

    public abstract OrderGroup order();

    public abstract String symbol();

    public abstract T shrink();

    @Override
    public String toString() {
        return symbol();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
