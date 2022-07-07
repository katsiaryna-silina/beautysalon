package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.ServDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.ServiceMapper;
import by.silina.beautysalon.mapper.impl.ServiceMapperImpl;
import by.silina.beautysalon.model.entity.Serv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServDaoImpl extends BaseDao<Serv> implements ServDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final ServiceMapper serviceMapper = ServiceMapperImpl.getInstance();
    private static final ServDaoImpl instance = new ServDaoImpl();
    private static final String SELECT_SERVICES = """
            SELECT ID, NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE, IS_DEPRECATED
            FROM SERVICES
            WHERE IS_COMPLEX = ? AND IS_DEPRECATED = 'N'
            """;
    private static final String SELECT_SERVICE_BY_NAME = """
            SELECT ID, NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE, IS_DEPRECATED
            FROM SERVICES
            WHERE NAME = ?
            """;
    private static final String UPDATE_SERVICE_TO_DEPRECATED = """
            UPDATE SERVICES
            SET IS_DEPRECATED = 'Y'
            WHERE ID = ?
            """;
    private static final String SELECT_NUMBER_OF_SERVICES = """
            SELECT COUNT(ID)
            FROM SERVICES;
            """;
    private static final String SELECT_PAGED_SERVICES = """
            SELECT ID, NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE, IS_DEPRECATED
            FROM SERVICES
            WHERE ID > ? LIMIT ?
            """;
    private static final String UPDATE_SERVICE_TO_NOT_DEPRECATED = """
            UPDATE SERVICES
            SET IS_DEPRECATED = 'N'
            WHERE ID = ?
            """;

    private ServDaoImpl() {
    }

    public static ServDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Serv> findServices(boolean complex) throws DaoException {
        List<Serv> complexServices = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICES)) {

            preparedStatement.setString(1, complex ? "Y" : "N");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Serv serviceFromResultSet = serviceMapper.toEntity(resultSet);
                    complexServices.add(serviceFromResultSet);
                }
            }
            return complexServices;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Serv> findServiceByName(String name) throws DaoException {
        Optional<Serv> optionalService = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICE_BY_NAME)) {

            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Serv serviceFromResultSet = serviceMapper.toEntity(resultSet);
                    optionalService = Optional.of(serviceFromResultSet);
                }
            }
            return optionalService;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public long findNumberOfServices() throws DaoException {
        long numberOfServices = 0L;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NUMBER_OF_SERVICES)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    numberOfServices = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return numberOfServices;
    }

    @Override
    public List<Serv> findPagedServiceList(Long fromServiceId, Integer numberOfServicesPerPage) throws DaoException {
        List<Serv> services = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAGED_SERVICES)) {

            preparedStatement.setLong(1, fromServiceId);
            preparedStatement.setInt(2, numberOfServicesPerPage);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Serv service = serviceMapper.toEntity(resultSet);
                    services.add(service);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return services;
    }

    @Override
    public boolean updateById(Long id) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_TO_NOT_DEPRECATED)) {

                preparedStatement.setLong(1, id);

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("Service with id={} was return to client's select.", id);
                    return true;
                } else {
                    connection.rollback();
                    log.debug("Service with id={} was not return to client's select. Transaction has being rolled back", id);
                    return false;
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Cannot rollback transaction.", ex);
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_TO_DEPRECATED)) {

                preparedStatement.setLong(1, id);

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("Service with id={} was deleted from client's select.", id);
                    return true;
                } else {
                    connection.rollback();
                    log.debug("Service with id={} was not deleted from client's select. Transaction has being rolled back", id);
                    return false;
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Cannot rollback transaction.", ex);
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
    }

    @Override
    public boolean insert(Serv serv) throws DaoException {
        throw new UnsupportedOperationException("Method insert() is unsupported.");
    }
}
