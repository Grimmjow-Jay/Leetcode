package com.jay.calculator.expressions;

import com.jay.calculator.OrderGroup;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public abstract class SymbolExpression extends Expression implements Cloneable {

    public SymbolExpression() {
    }

    public abstract OrderGroup order();

    public abstract String symbol();

    public abstract Expression shrink(Expression head);

    @Override
    public String toString() {
        return symbol();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
