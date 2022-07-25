package by.silina.beautysalon.connection;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * The ProxyConnection record class adds reallyClose() method and implements all methods of the Connection class.
 * Cause record the class has a package-private constructor with one parameter and method get() of this parameter.
 * Also, the record class has equals(), hashCode() and toString() methods.
 *
 * @param connection Connection private final field of this class that can be defined in the constructor
 * @author Silina Katsiaryna
 */
record ProxyConnection(Connection connection) implements Connection {

    /**
     * Closes connection.
     *
     * @throws SQLException if a database access error occurs.
     */
    void reallyClose() throws SQLException {
        connection.close();
    }

    /**
     * Releases connection to the connection pool.
     */
    @Override
    public void close() {
        ConnectionPool.getInstance().releaseConnection(this);
    }

    /**
     * Checks if the connection is closed.
     *
     * @return boolean. True if this Connection object is closed; false if it is still open.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    /**
     * Creates a statement.
     *
     * @return Statement. A new default Statement object.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Prepares a PreparedStatement.
     *
     * @param sql String. An SQL statement that may contain one or more '?' IN parameter placeholders.
     * @return PreparedStatement. A new default PreparedStatement object containing the pre-compiled SQL statement.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     * Prepares a CallableStatement.
     *
     * @param sql String. An SQL statement that may contain one or more '?' IN parameter placeholders.
     * @return CallableStatement. A new default CallableStatement object containing the pre-compiled SQL statement.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    /**
     * Converts the given SQL statement into the system's native SQL grammar.
     *
     * @param sql String. An SQL statement that may contain one or more '?' IN parameter placeholders.
     * @return String. The native form of this statement.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    /**
     * Retrieves the current auto-commit mode for this Connection object.
     *
     * @return boolean. The current state of this Connection object's auto-commit mode.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    /**
     * Sets this connection's auto-commit mode to the given state.
     *
     * @param autoCommit boolean. True to enable auto-commit mode; false to disable it.
     * @throws SQLException if a database access error occurs,
     *                      setAutoCommit(true) is called while participating in a distributed transaction,
     *                      or this method is called on a closed connection.
     */
    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    /**
     * Commits all changes. This method should be used only when auto-commit mode has been disabled.
     *
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a distributed transaction,
     *                      if this method is called on a closed connection
     *                      or this Connection object is in auto-commit mode.
     */
    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * Rollbacks all changes. This method should be used only when auto-commit mode has been disabled.
     *
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a distributed transaction,
     *                      if this method is called on a closed connection
     *                      or this Connection object is in auto-commit mode.
     */
    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Gets metadata about the database to which this Connection object represents a connection.
     *
     * @return DatabaseMetaData. A DatabaseMetaData object for this Connection object.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    /**
     * Retrieves whether this Connection object is in read-only mode.
     *
     * @return boolean. True if this Connection object is read-only; false otherwise.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    /**
     * Puts this connection in read-only mode as a hint to the driver to enable database optimizations.
     *
     * @param readOnly boolean. True enables read-only mode; false disables it.
     * @throws SQLException if a database access error occurs,
     *                      this method is called on a closed connection or this method is.
     */
    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    /**
     * Gets catalog name.
     *
     * @return String. The current catalog name or null if there is none.
     * @throws SQLException if a database access error occurs,
     *                      this method is called on a closed connection or this method is.
     */
    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    /**
     * Sets catalog name.
     *
     * @param catalog String. The name of a catalog.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public void setCatalog(String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    /**
     * Retrieves a transaction isolation level.
     *
     * @return int.The current transaction isolation level.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    /**
     * Sets a transaction isolation level.
     *
     * @param level int. The transaction isolation level.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    /**
     * Gets SQLWarning.
     *
     * @return SQLWarning. The first SQLWarning object or null if there are none.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    /**
     * Clears all warnings.
     *
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    /**
     * Creates Statement.
     *
     * @param resultSetType        int. The result set type.
     * @param resultSetConcurrency int. The concurrency type.
     * @return Statement.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * Prepares PreparedStatement.
     *
     * @param sql                  String. The SQL statement to be sent to the database;
     *                             may contain one or more '?' IN parameters
     * @param resultSetType        int. The result set type.
     * @param resultSetConcurrency int. The concurrency type.
     * @return PreparedStatement.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * Prepares CallableStatement.
     *
     * @param sql                  String. The SQL statement to be sent to the database;
     *                             may contain one or more '?' IN parameters
     * @param resultSetType        int. The result set type.
     * @param resultSetConcurrency int. The concurrency type.
     * @return CallableStatement.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * Gets map object associated with this Connection object.
     *
     * @return Map. Object associated with this Connection object.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    /**
     * Sets map object as the type map for this Connection object.
     *
     * @param map Map. Object associated with this Connection object.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    /**
     * Gets the current holdability of ResultSet objects created using this Connection object.
     *
     * @return int. The holdability, one of ResultSet.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    /**
     * Sets the holdability.
     *
     * @param holdability int. A ResultSet holdability constant.
     * @throws SQLException if a database access occurs,
     *                      this method is called on a closed connection,
     *                      or the given parameter is not a ResultSet constant indicating holdability.
     */
    @Override
    public void setHoldability(int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    /**
     * Sets an unnamed savepoint in the current transaction
     * and returns the new Savepoint object that represents it.
     *
     * @return Savepoint. The new Savepoint object.
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a distributed transaction,
     *                      this method is called on a closed connection
     *                      or this Connection object is currently in auto-commit mode.
     */
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    /**
     * Sets an unnamed savepoint in the current transaction
     * and returns the new Savepoint object that represents it.
     *
     * @param name String. The name of the savepoint.
     * @return Savepoint. The new Savepoint object.
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a distributed transaction,
     *                      this method is called on a closed connection
     *                      or this Connection object is currently in auto-commit mode.
     */
    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    /**
     * Rollbacks all changes made after the given Savepoint object was set.
     *
     * @param savepoint Savepoint. The Savepoint object to roll back to.
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a distributed transaction,
     *                      this method is called on a closed connection
     *                      or this Connection object is currently in auto-commit mode.
     */
    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    /**
     * Removes the specified Savepoint.
     *
     * @param savepoint Savepoint. The Savepoint object to be removed.
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a distributed transaction,
     *                      this method is called on a closed connection
     *                      or this Connection object is currently in auto-commit mode.
     */
    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    /**
     * Creates Statement.
     *
     * @param resultSetType        int. ResultSet constant.
     * @param resultSetConcurrency int. ResultSet constant.
     * @param resultSetHoldability int. ResultSet constant.
     * @return Statement
     * @throws SQLException if a database access error occurs,
     *                      this method is called on a closed connection
     *                      or the given parameters are not ResultSet constants indicating type, concurrency, and holdability.
     */
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * Prepares PreparedStatement.
     *
     * @param sql                  String. The SQL statement to be sent to the database;
     *                             may contain one or more '?' IN parameters
     * @param resultSetType        int. ResultSet constant.
     * @param resultSetConcurrency int. ResultSet constant.
     * @param resultSetHoldability int. ResultSet constant.
     * @return PreparedStatement
     * @throws SQLException if a database access error occurs,
     *                      this method is called on a closed connection
     *                      or the given parameters are not ResultSet constants indicating type, concurrency, and holdability.
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * Prepares CallableStatement.
     *
     * @param sql                  String. The SQL statement to be sent to the database;
     *                             may contain one or more '?' IN parameters
     * @param resultSetType        int. ResultSet constant.
     * @param resultSetConcurrency int. ResultSet constant.
     * @param resultSetHoldability int. ResultSet constant.
     * @return CallableStatement
     * @throws SQLException if a database access error occurs,
     *                      this method is called on a closed connection
     *                      or the given parameters are not ResultSet constants indicating type, concurrency, and holdability.
     */
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * Prepares PreparedStatement that has the capability to retrieve auto-generated keys.
     *
     * @param sql               String. The SQL statement to be sent to the database;
     *                          may contain one or more '?' IN parameters
     * @param autoGeneratedKeys int. A flag indicating whether auto-generated keys should be returned; one of Statement.
     * @return PreparedStatement
     * @throws SQLException if a database access error occurs,
     *                      this method is called on a closed connection
     *                      or the given parameter is not a Statement constant indicating
     *                      whether auto-generated keys should be returned.
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    /**
     * Prepares PreparedStatement.
     *
     * @param sql           String. The SQL statement to be sent to the database;
     *                      may contain one or more '?' IN parameters
     * @param columnIndexes int[]. An array of column indexes indicating the columns
     *                      that should be returned from the inserted row or rows.
     * @return PreparedStatement
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    /**
     * Prepares PreparedStatement.
     *
     * @param sql         String. The SQL statement to be sent to the database;
     *                    may contain one or more '?' IN parameters
     * @param columnNames String[]. An array of column names indicating the columns
     *                    that should be returned from the inserted row or rows.
     * @return PreparedStatement
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    /**
     * Creates Clob.
     *
     * @return Clob
     * @throws SQLException if an object that implements the Clob interface can not be constructed,
     *                      this method is called on a closed connection
     *                      or a database access error occurs.
     */
    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    /**
     * Creates Blob.
     *
     * @return Blob
     * @throws SQLException if an object that implements the Blob interface can not be constructed,
     *                      this method is called on a closed connection
     *                      or a database access error occurs.
     */
    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    /**
     * Creates NClob.
     *
     * @return NClob
     * @throws SQLException if an object that implements the NClob interface can not be constructed,
     *                      this method is called on a closed connection
     *                      or a database access error occurs.
     */
    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    /**
     * Creates SQLXML.
     *
     * @return SQLXML
     * @throws SQLException if an object that implements the SQLXML interface can not be constructed,
     *                      this method is called on a closed connection
     *                      or a database access error occurs.
     */
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    /**
     * Returns true if the connection has not been closed and is still valid.
     *
     * @param timeout int. The time in seconds to wait for the database operation.
     * @return boolean.
     * @throws SQLException if the value supplied for timeout is less than 0.
     */
    @Override
    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    /**
     * Sets client info.
     *
     * @param name  String. The name of the client info property to set.
     * @param value String. The value to set the client info property to.
     * @throws SQLClientInfoException if the database server returns an error
     *                                while setting the client info value on the database server
     *                                or this method is called on a closed connection.
     */
    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    /**
     * Gets client info.
     *
     * @param name String. The name of the client info property to retrieve.
     * @return String. The value of the client info property specified.
     * @throws SQLException if the database server returns an error
     *                      when fetching the client info value from the database
     *                      or this method is called on a closed connection.
     */
    @Override
    public String getClientInfo(String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    /**
     * Gets client info.
     *
     * @return Properties.
     * @throws SQLException if the database server returns an error
     *                      when fetching the client info value from the database
     *                      or this method is called on a closed connection.
     */
    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    /**
     * Sets client info.
     *
     * @param properties Properties. The list of client info properties to set.
     * @throws SQLClientInfoException if the database server returns an error
     *                                while setting the client info value on the database server
     *                                or this method is called on a closed connection.
     */
    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    /**
     * Creates Array.
     *
     * @param typeName String. The SQL name of the type the elements of the array map to.
     * @param elements Object[]. The elements that populate the returned object.
     * @return Array.
     * @throws SQLException if a database error occurs,
     *                      the JDBC type is not appropriate for the typeName and the conversion is not supported,
     *                      the typeName is null or this method is called on a closed connection.
     */
    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    /**
     * Creates Struct.
     *
     * @param typeName   String. The SQL name of the type the elements of the array map to.
     * @param attributes Object[]. The elements that populate the returned object.
     * @return Array.
     * @throws SQLException if a database error occurs,
     *                      the typeName is null or this method is called on a closed connection.
     */
    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    /**
     * Retrieves this Connection object's current schema name.
     *
     * @return Array.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    /**
     * Sets the given schema name to access.
     *
     * @param schema String. The name of a schema in which to work.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public void setSchema(String schema) throws SQLException {
        connection.setSchema(schema);
    }

    /**
     * Terminates an open connection.
     *
     * @param executor Executor. The Executor implementation which will be used by abort.
     * @throws SQLException if a database access error occurs or the executor is null,
     *                      or this method is called on a closed connection.
     */
    @Override
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    /**
     * Sets the maximum period to wait for the database to reply to any request.
     *
     * @param executor     Executor. The Executor implementation which will be used by setNetworkTimeout.
     * @param milliseconds int. The time in milliseconds to wait for the database operation to complete.
     * @throws SQLException if a database access error occurs, this method is called on a closed connection,
     *                      the executor is null,
     *                      or the value specified for seconds is less than 0.
     */
    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    /**
     * Retrieves the number of milliseconds the driver will wait for a database request to complete.
     *
     * @return int. The current timeout limit in milliseconds; zero means there is no limit.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }


    /**
     * Returns an object that implements the given interface to allow access to non-standard methods,
     * or standard methods not exposed by the proxy.
     *
     * @param iface Class. A Class defining an interface that the result must implement.
     * @return An object that implements the interface.
     * @throws SQLException if no object found that implements the interface.
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }


    /**
     * Returns true if this either implements the interface argument or is directly
     * or indirectly a wrapper for an object that does.
     * Returns false otherwise.
     *
     * @param iface Class. A Class defining an interface that the result must implement.s
     * @return boolean. True if this implements the interface or directly or indirectly wraps an object that does.
     * @throws SQLException if an error occurs while determining
     *                      whether this is a wrapper for an object with the given interface.
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }
}
