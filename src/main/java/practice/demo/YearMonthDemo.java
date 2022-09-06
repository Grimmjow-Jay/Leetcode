package practice.demo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

/**
 * @author Jay Yang
 * @date 2022/8/30
 */
public class YearMonthDemo {

    public static void main(String[] args) {

        YearMonth yearMonth = YearMonth.parse("2022-03");

        LocalDateTime startTime = LocalDateTime.of(yearMonth.atDay(1), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(yearMonth.atEndOfMonth(), LocalTime.MAX);

        System.out.println(startTime);
        System.out.println(endTime);

    }

}
