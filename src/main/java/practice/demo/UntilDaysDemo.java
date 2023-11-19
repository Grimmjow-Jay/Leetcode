package practice.demo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Jay Yang
 * @date 2023/11/19
 */
public class UntilDaysDemo {

    public static void main(String[] args) {

        LocalDateTime tempTime = LocalDateTime.of(2023, 11, 19, 23, 45);
        LocalDateTime next = tempTime.plusMinutes(15);

        long until1 = tempTime.toLocalDate().until(next.toLocalDate(), ChronoUnit.DAYS);
        System.out.println(until1);

        long until2 = tempTime.until(next, ChronoUnit.DAYS);
        System.out.println(until2);

    }

}
