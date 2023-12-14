package practice.demo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Jay Yang
 * @date 2023/12/14
 */
public class LocalDateTimeStampDemo {

    public static void main(String[] args) {
        String timestamp = "1490867741000";
        LocalDateTime invoiceTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(Long.parseLong(timestamp)),
                ZoneId.systemDefault());
        System.out.println(invoiceTime);
    }

}
