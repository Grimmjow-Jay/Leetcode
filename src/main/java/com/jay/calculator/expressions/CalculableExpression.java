package com.jay.calculator.expressions;

import java.math.BigDecimal;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public abstract class CalculableExpression extends Expression {

    public abstract BigDecimal calculate();

}
