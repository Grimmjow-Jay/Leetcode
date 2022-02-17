package com.jay.calculator;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jay Yang
 * @date 2022/2/9
 */
public enum OrderGroup {

    /**
     * 括号
     */
    BRACKET,

    /**
     * 三角函数
     */
    TRIGONOMETRIC,
    /**
     * 乘、除
     */
    MULTIPLY_DIVIDE,

    /**
     * 加、减
     */
    ADD_SUBTRACT;


    public static List<OrderGroup> getOrder() {
        return Arrays.asList(OrderGroup.values());
    }

}
