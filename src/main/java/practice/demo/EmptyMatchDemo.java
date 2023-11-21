package practice.demo;

import java.util.Collections;
import java.util.List;

/**
 * @author Jay Yang
 * @date 2023/11/21
 */
public class EmptyMatchDemo {

    @SuppressWarnings("all")
    public static void main(String[] args) {

        List<String> emptyList = Collections.emptyList();

        boolean allMatch = emptyList.stream()
                .allMatch(e -> e.equals("a"));
        System.out.println(allMatch);

        boolean anyMatch = emptyList.stream()
                .anyMatch(e -> e.equals("a"));
        System.out.println(anyMatch);

        boolean noneMatch = emptyList.stream()
                .noneMatch(e -> e.equals("a"));
        System.out.println(noneMatch);

    }

}
