package practice.demo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Jay Yang
 * @date 2023/10/19
 */
public class TemporalAdjustersDemo {

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();

        LocalDate startDayOfCurrentWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        System.out.println(startDayOfCurrentWeek);
        LocalDateTime startTimeOfCurrentWeek = startDayOfCurrentWeek.atStartOfDay();
        System.out.println(startTimeOfCurrentWeek);

        LocalDate endDayOfCurrentWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(endDayOfCurrentWeek);
        LocalDateTime endTimeOfCurrentWeek = LocalDateTime.of(endDayOfCurrentWeek, LocalTime.MAX);
        System.out.println(endTimeOfCurrentWeek);
    }

}
