package practice.demo;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Jay Yang
 * @date 2023/11/22
 */
public class DateTimeFormatDemo {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\jay\\Desktop\\temp\\临时图片\\example.jpg");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        String filePrefix = DateTimeFormatter.ofPattern("yyyy_MM/yyyyMMddHHmmss_").format(now);
        String fileKey = filePrefix + file.getName();
        System.out.println(fileKey);
    }


}
