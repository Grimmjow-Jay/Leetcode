package practice.demo;

import java.lang.reflect.Array;

/**
 * @author Jay Yang
 * @date 2023/1/4
 */
public class ArrayNewInstanceDemo {

    public static void main(String[] args) {

        Object o1 = Array.newInstance(String.class, 1);
        System.out.println(o1.getClass());

        Object o2 = Array.newInstance(String[].class, 1);
        System.out.println(o2.getClass());
        System.out.println(o2.getClass().getComponentType());

    }

}
