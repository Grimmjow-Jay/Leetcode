package practice.demo.pool;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jay Yang
 * @date 2022/1/21
 */
@RequiredArgsConstructor
public class WithoutPoolConnectionFactory implements ConnectionFactory {

    private final String url;
    private final String user;
    private final String password;

    @Override
    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, user, password);

    }

}
