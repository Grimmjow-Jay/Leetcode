package com.jay.calculator;

import com.jay.calculator.expressions.CalculableExpression;
import com.jay.calculator.expressions.Expression;
import com.jay.calculator.expressions.NumberExpression;
import com.jay.calculator.expressions.SymbolExpression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public class SimpleCalculator {

    private final SymbolExpressionFactory symbolExpressionFactory;

    public SimpleCalculator() {
        this(new NormalSymbolExpressionFactory());
    }

    public SimpleCalculator(SymbolExpressionFactory symbolExpressionFactory) {
        this.symbolExpressionFactory = symbolExpressionFactory;
    }

    public BigDecimal calculate(String expression) {

        Expression head = analysis(expression);

        CalculableExpression calculableExpression = shrink(head);

        return calculableExpression.calculate();

    }

    private Expression analysis(String expression) {

        List<Expression> expressions = new ArrayList<>();
        Boolean lastIsNumber = null;
        int from = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            boolean isNumber = Character.isDigit(c) || c == '.';
            if (lastIsNumber == null) {
                lastIsNumber = isNumber;
                continue;
            }

            String lastExpression = expression.substring(from, i);
            if (!isNumber) {
                if (lastIsNumber) {
                    // 123+22
                    //    ↑
                    expressions.add(new NumberExpression(lastExpression));
                    from = i;
                } else {
                    // 123+sin45
                    //     ↑
                    if (symbolExpressionFactory.effective(lastExpression)) {
                        expressions.add(symbolExpressionFactory.create(lastExpression));
                        from = i;
                    }
                }
            } else {
                if (!lastIsNumber) {
                    // 123+sin45
                    //        ↑
                    expressions.add(symbolExpressionFactory.create(lastExpression));
                    from = i;
                }
            }
            lastIsNumber = isNumber;

        }

        String last = expression.substring(from);
        if (symbolExpressionFactory.effective(last)) {
            expressions.add(symbolExpressionFactory.create(last));
        } else {
            expressions.add(new NumberExpression(last));
        }

        int size = expressions.size();
        for (int i = 1; i < size; i++) {
            Expression.join(expressions.get(i - 1), expressions.get(i));
        }
        return expressions.get(0);
    }

    public CalculableExpression shrink(Expression head) {

        List<OrderGroup> orderGroups = OrderGroup.getOrder();
        for (OrderGroup orderGroup : orderGroups) {

            head = shrink(head, orderGroup);

        }

        return (CalculableExpression) head;
    }

    private Expression shrink(Expression head, OrderGroup orderGroup) {

        Expression node = head;
        while (node != null) {

            if (node instanceof SymbolExpression) {
                SymbolExpression symbolExpression = (SymbolExpression) node;
                if (orderGroup.equals(symbolExpression.order())) {
                    head = symbolExpression.shrink(head);
                    node = head;
                    continue;
                }
            }

            node = node.next();
        }

        return head;
    }

}
