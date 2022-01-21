package practice.demo.pool;

import lombok.RequiredArgsConstructor;

import java.io.Closeable;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jay Yang
 * @date 2022/1/21
 */
@RequiredArgsConstructor
public class PoolConnectionFactory implements ConnectionFactory, Closeable {

    private final String url;
    private final String user;
    private final String password;
    private final int size;

    private final Queue<Connection> connections = new LinkedList<>();
    private int count = 0;

    @Override
    public Connection getConnection() throws SQLException {

        Connection connection = getFromPool();

        return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(),
                connection.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Method close = Connection.class.getMethod("close");
                    if (close.equals(method)) {
                        returnPool(connection);
                        return null;
                    } else {
                        return method.invoke(connection, args);
                    }
                });
    }

    private final Object lock = new Object();

    private Connection getFromPool() throws SQLException {
        synchronized (lock) {

            if (connections.isEmpty()) {
                if (count > size) {
                    throw new SQLException("too many connection");
                } else {
                    connections.offer(DriverManager.getConnection(url, user, password));
                    count++;
                }
            }
            return connections.poll();
        }
    }

    private void returnPool(Connection connection) {
        synchronized (lock) {
            connections.offer(connection);
        }
    }

    @Override
    public void close() {

        for (Connection connection : connections) {
            try {
                connection.close();
                count--;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
