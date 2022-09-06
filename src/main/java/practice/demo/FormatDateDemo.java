package practice.demo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Jay Yang
 * @date 2022/8/18
 */
public class FormatDateDemo {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) {

        String format = new FormatDateDemo().formatDate(LocalDateTime.of(2022, 5, 3, 0, 0, 0));
        System.out.println(format);
    }

    private String formatDate(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        return LocalTime.MIN.compareTo(date.toLocalTime()) == 0
                ? DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(date)
                : DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT).format(date);
    }

}
