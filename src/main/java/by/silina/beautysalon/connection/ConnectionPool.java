package by.silina.beautysalon.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    public static final String NUMBER_OF_CONNECTIONS = "number_of_connections";
    public static final String URL = "url";
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String DATABASE_PROPERTIES_PATH = "config/database.properties";
    private static final int DEFAULT_NUMBER_OF_CONNECTIONS = 5;
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean connectionPoolInstanceExists = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> occupiedConnections;
    private int numberOfConnections;

    private ConnectionPool() {
        try (InputStream dbPropertiesStream = ConnectionPool.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_PATH)) {
            if (dbPropertiesStream == null) {
                throw new FileNotFoundException("Unable to find " + DATABASE_PROPERTIES_PATH);
            }
            Properties properties = new Properties();
            properties.load(dbPropertiesStream);

            try {
                numberOfConnections = Integer.parseInt(properties.getProperty(NUMBER_OF_CONNECTIONS));
            } catch (NumberFormatException e) {
                numberOfConnections = DEFAULT_NUMBER_OF_CONNECTIONS;
                log.warn("Incorrect number of connections in property file. Set default value = {}", DEFAULT_NUMBER_OF_CONNECTIONS);
            }
            freeConnections = new LinkedBlockingQueue<>(numberOfConnections);
            occupiedConnections = new LinkedBlockingQueue<>(numberOfConnections);

            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            String url = properties.getProperty(URL);
            for (int i = 0; i < numberOfConnections; i++) {
                Connection connection = DriverManager.getConnection(url, properties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            }
        } catch (SQLException | IOException e) {
            log.error("Connection pool initialization error.", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!connectionPoolInstanceExists.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    connectionPoolInstanceExists.set(true);
                    log.debug("Connection pool was created with number of connections = {}.", instance.numberOfConnections);
                }
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
            occupiedConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Connection interrupted during trying to get connection.", e);
        }
        return proxyConnection;
    }

    public boolean releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection proxyConnection) {
            occupiedConnections.remove(proxyConnection);
            try {
                freeConnections.put(proxyConnection);
                return true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Connection interrupted during trying to release connection.", e);
            }
        }
        return false;
    }

    public void destroyPool() {
        closeAllConnections();
        deregisterDrivers();
        log.debug("Connection pool was destroyed.");
    }

    private void closeAllConnections() {
        for (int i = 0; i < numberOfConnections; i++) {
            try {
                ProxyConnection proxyConnection = freeConnections.take();
                proxyConnection.reallyClose();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Connection pool closing is failed.", e);
            } catch (SQLException e) {
                log.error("Connection pool closing is failed.", e);
            }
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                log.error("Cannot deregister driver = {}", driver, e);
            }
        });
    }
}
