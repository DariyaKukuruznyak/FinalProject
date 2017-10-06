package dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionPoolTest {
    @Test
    public void connectionPoolNotNull() {
        assertNotNull(ConnectionPool.getInstance());
    }

    @Test
    public void connectionPoolIsSingleton() {
        ConnectionPool instance = ConnectionPool.getInstance();
        assertSame(instance, ConnectionPool.getInstance());
    }

    @Test
    public void getConnection() {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnectionPool().getConnection();
        } catch (SQLException e) {
            fail();
            e.printStackTrace();
        }
        assertNotNull(connection);
    }
}
