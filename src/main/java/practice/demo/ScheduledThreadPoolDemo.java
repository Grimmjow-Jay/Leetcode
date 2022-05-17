package practice.demo;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Jay Yang
 * @date 2022/5/17
 */
public class ScheduledThreadPoolDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        Runnable runnable = () -> System.out.println(System.currentTimeMillis());
        scheduledThreadPoolExecutor.scheduleAtFixedRate(
                runnable,
                2000L,
                2000L,
                TimeUnit.MILLISECONDS);

    }

}
