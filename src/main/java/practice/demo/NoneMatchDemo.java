package practice.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jay Yang
 * @date 2023/7/20
 */
public class NoneMatchDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        boolean without2 = list.stream()
                .noneMatch(e -> e.equals("2"));
        if (without2) {
            System.out.println("aaa");
        } else {
            System.out.println("bbb");
        }
    }

}
