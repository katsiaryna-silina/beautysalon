package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.ServDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.ServMapper;
import by.silina.beautysalon.mapper.impl.ServMapperImpl;
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
    private static final String SELECT_SERVICES = """
            SELECT ID, NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE
            FROM SERVICES
            WHERE IS_COMPLEX = ?
            """;
    private static final String SELECT_SERVICE_BY_NAME = """
            SELECT ID, NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE
            FROM SERVICES
            WHERE NAME = ?
            """;
    private static final ServMapper serviceMapper = ServMapperImpl.getInstance();
    private static final ServDaoImpl instance = new ServDaoImpl();

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
    public boolean insert(Serv serv) throws DaoException {
        return false;
    }

    @Override
    public Serv update(Serv serv) throws DaoException {
        return null;
    }
}
