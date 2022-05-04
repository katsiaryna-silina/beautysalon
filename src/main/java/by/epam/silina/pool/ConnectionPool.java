package by.epam.silina.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.silina.controller.Constant.POOL_SIZE;

public class ConnectionPool {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Lock lock = new ReentrantLock();
    private static final Driver driver;
    private static ConnectionPool instance;

    static {
        try {
            // var 1 - exception on compilation
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            // var 2 - reflection api - exception on runtime
            // Class.forName("com.mysql.jdbc.Driver");
       /* } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }*/
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    //sum of two queues = 8
    private final BlockingQueue<ProxyConnection> freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);
    //check целостность pool
    private final BlockingQueue<ProxyConnection> usedConnections = new LinkedBlockingQueue<>(POOL_SIZE);

    private ConnectionPool() {

        //todo read from file properties and add exceptions
        String url = "jdbc:mysql://localhost:3306/SALON";

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "1111");

        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection proxyConnection;
            try {
                Connection connection = DriverManager.getConnection(url, properties);
                proxyConnection = new ProxyConnection(connection);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
            freeConnections.add(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                instance = new ConnectionPool();
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            usedConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            try {
                usedConnections.remove(connection);
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            throw new RuntimeException("Undefine connection.");
        }

    }

    private void deregisterDriver() {
        if (driver != null) {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                //todo specify log or exception
            }
        } else {
            log.warn("Cannot deregister driver. Driver is not registered.");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().close();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                //todo log
            } catch (SQLException e) {
                //todo log
            }
        }
        deregisterDriver();
    }
}
