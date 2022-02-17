package com.jay.calculator;

import com.jay.calculator.expressions.SymbolExpression;

/**
 * @author Jay Yang
 * @date 2022/2/10
 */
public interface SymbolExpressionFactory {

    SymbolExpression<?> create(String symbol);

    boolean effective(String symbol);

}
