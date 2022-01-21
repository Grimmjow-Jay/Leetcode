package practice.demo.pool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Jay Yang
 * @date 2022/1/21
 */
public interface ConnectionFactory {

    Connection getConnection() throws SQLException;

}
