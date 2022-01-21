package practice.demo.pool;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jay Yang
 * @date 2022/1/21
 */
public class DemoApplication {

    public static final String url = "jdbc:mysql://localhost:3306/jay_demo" +
            "?useUnicode=true" +
            "&characterEncoding=UTF-8" +
            "&allowMultiQueries=true" +
            "&useSSL=false" +
            "&allowPublicKeyRetrieval=true" +
            "&serverTimezone=GMT%2B8";
    public static final String user = "root";
    public static final String password = "123456";

    static {
        try {
            Class.forName(Driver.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        String sql = "SELECT COUNT(*) FROM hero";
        int sqlCount = 1000;

        WithoutPoolConnectionFactory withoutPoolConnectionFactory = new WithoutPoolConnectionFactory(url, user, password);
        test(sql, sqlCount, withoutPoolConnectionFactory);

        try (PoolConnectionFactory connectionFactory = new PoolConnectionFactory(url, user, password, 100)) {
            test(sql, sqlCount, connectionFactory);
        }

    }

    private static void test(String sql, int sqlCount, ConnectionFactory connectionFactory) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        CountDownLatch cdl1 = new CountDownLatch(sqlCount);
        AtomicLong cost = new AtomicLong();
        AtomicLong totalCount = new AtomicLong();
        for (int i = 0; i < sqlCount; i++) {
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                try {
                    totalCount.addAndGet(run(sql, connectionFactory));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                cost.addAndGet((end - start));
                cdl1.countDown();
            });
        }
        cdl1.await();
        executorService.shutdown();
        System.out.println(totalCount.get() + "  " + cost.get() + " ms.");
    }

    private static long run(String sql, ConnectionFactory connectionFactory) throws SQLException {
        long totalCount = 0L;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                long count = resultSet.getLong(1);
                totalCount += count;
            }
        }
        return totalCount;
    }


}
