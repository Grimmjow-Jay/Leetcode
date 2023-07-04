package practice.demo;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;

/**
 * @author Jay Yang
 * @date 2023/6/21
 */
public class LocalDateDemo {

    public static void main(String[] args) throws MalformedURLException {

        YearMonth period = YearMonth.of(2023, Month.JANUARY);
        LocalDateTime redoCalculateBeginTime = LocalDate.of(period.getYear(), period.getMonth(), 1)
                .atStartOfDay();

        YearMonth nextPeriod = period.plusMonths(1);
        LocalDateTime redoCalculateEndTime = LocalDate.of(nextPeriod.getYear(), nextPeriod.getMonth(), 1)
                .atStartOfDay()
                .minusSeconds(1);

        System.out.println(redoCalculateBeginTime);
        System.out.println(redoCalculateEndTime);

    }

}
