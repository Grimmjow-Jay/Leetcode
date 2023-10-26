package practice.demo;

import java.time.LocalDateTime;

/**
 * @author Jay Yang
 * @date 2023/10/25
 */
public class LocalDateTimeParseDemo {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        String nowString = now.toString();
        System.out.println(nowString);

        LocalDateTime parse = LocalDateTime.parse(nowString);
        System.out.println(parse);
    }

}
