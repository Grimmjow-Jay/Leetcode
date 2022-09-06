package practice.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jay Yang
 * @date 2022/8/29
 */
public class StreamMatchDemo {

    public static void main(String[] args) {
        String tempTableName = "zhihui_statistics_temp_" + Thread.currentThread().getName() + "_" +System.currentTimeMillis();
        System.out.println(tempTableName);

        tempTableName = "zhihui_statistics_temp_" + Thread.currentThread().getName() + "_" +System.currentTimeMillis();
        System.out.println(tempTableName);

        List<String> list = new ArrayList<>();
        boolean allMatch = list.stream()
                .allMatch(e -> e.contains("sdfsdfsdfsdf"));
        System.out.println(allMatch);
    }

}
