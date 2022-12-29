package practice.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jay Yang
 * @date 2022/12/28
 */
public class AnyMatchDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bcd");

        boolean anyMatch = list.stream()
                .anyMatch("abc"::equalsIgnoreCase);
        System.out.println(anyMatch);

    }

}
