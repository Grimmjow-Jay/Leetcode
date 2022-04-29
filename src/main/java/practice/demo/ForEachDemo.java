package practice.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jay Yang
 * @date 2022/4/15
 */
public class ForEachDemo {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("a", "b", "c");

        stringList.forEach(s -> {

            if (s.equals("b")) {
                return;
            }

            System.out.println(s);
        });

    }

}
