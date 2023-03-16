package practice.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

/**
 * @author Jay Yang
 * @date 2023/3/7
 */
public class LocalDateTimeDemo {

    public static void main(String[] args) {

        YearMonth yearMonth = YearMonth.now();
        LocalDateTime endTime = specialMonthEndTime(yearMonth);
        System.out.println(endTime);

        LocalDateTime beginningOfThirtyDaysAgo = endTime.minusDays(29).toLocalDate().atStartOfDay();
        LocalDate date = beginningOfThirtyDaysAgo.toLocalDate();
        LocalDate endDate = endTime.toLocalDate();
        while (!date.isAfter(endDate)) {
            System.out.println(date);
            date = date.plusDays(1);
        }
    }

    /**
     * 指定月份最晚时间，若月份是当前月，返回当前时间，否则返回当月最后一天的23:59:59
     */
    private static LocalDateTime specialMonthEndTime(YearMonth yearMonth) {
        if (yearMonth.equals(YearMonth.now())) {
            return LocalDateTime.now();
        }
        return LocalDateTime.of(yearMonth.atEndOfMonth(), LocalTime.MAX);
    }


}
