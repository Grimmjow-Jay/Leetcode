package practice.demo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Jay Yang
 * @date 2022/8/19
 */
public class OomDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {

            byte[] bytes = new byte[300 * 1000 * 1000];
            System.out.println(bytes.length);

        });

        Thread thread2 = new Thread(() -> {

            try {
                runSql();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("run sql finished");

        });

        thread2.start();
        thread1.start();

        thread2.join();

    }

    private static void runSql() throws SQLException {
        try {
            LockSupport.parkUntil(System.currentTimeMillis() + 2000L);
        } catch (Throwable e) {
            throw new SQLException(e.getMessage());
        }
    }

}
