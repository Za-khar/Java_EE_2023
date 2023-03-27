package connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyConnectionPool implements ConnectionPool{

    private static MyConnectionPool instance;

    private static final int INITIAL_POOL_SIZE = 15;
    private static final int MAX_POOL_SIZE = 100;
    private static final int MAX_TIMEOUT = 5;

    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();

    public static synchronized MyConnectionPool getInstance(){
        if (instance == null) {
            instance = create();
        }
        return instance;
    }

    private MyConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }

    public static MyConnectionPool create() {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                pool.add(createConnection());
            } catch (NamingException | SQLException e) {
                e.printStackTrace();
            }
        }
        return new MyConnectionPool(pool);
    }

    private static Connection createConnection() throws NamingException, SQLException {
        Context initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/student");

        return dataSource.getConnection();
    }

    @Override
    public synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                try {
                    connectionPool.add(createConnection());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);

        try {
            if (!connection.isValid(MAX_TIMEOUT)) {
                try {
                    connection = createConnection();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        usedConnections.add(connection);
        return connection;
    }


    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
}
