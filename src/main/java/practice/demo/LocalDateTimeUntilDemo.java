package practice.demo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Jay Yang
 * @date 2022/12/1
 */
public class LocalDateTimeUntilDemo {

    public static void main(String[] args) throws InterruptedException {

        LocalDateTime from = LocalDateTime.parse("2022-12-01T02:48:11");
        LocalDateTime end = LocalDateTime.parse("2022-12-01T02:52:11");

        System.out.println(from.until(end, ChronoUnit.MILLIS));
        System.out.println(end.until(from, ChronoUnit.MILLIS));
    }

}
