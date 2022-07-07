package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.OrderStatusDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.OrderStatusMapper;
import by.silina.beautysalon.mapper.impl.OrderStatusMapperImpl;
import by.silina.beautysalon.model.entity.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderStatusDaoImpl extends BaseDao<OrderStatus> implements OrderStatusDao {
    private static final OrderStatusDaoImpl instance = new OrderStatusDaoImpl();
    private static final String SELECT_ORDER_STATUSES = """
            SELECT OS.ID, OS.STATUS, OS.DESCRIPTION, R.ROLE
            FROM ORDER_STATUSES OS
            JOIN USER_ROLES R ON R.ID = OS.ACCESSIBLE_TO_ROLE_ID
            """;
    private static final String SELECT_ORDER_STATUS_BY_NAME = """
            SELECT OS.ID, OS.STATUS, OS.DESCRIPTION, R.ROLE
            FROM ORDER_STATUSES OS
            JOIN USER_ROLES R ON R.ID = OS.ACCESSIBLE_TO_ROLE_ID
            WHERE OS.STATUS = ?
            """;
    private final OrderStatusMapper orderStatusMapper = OrderStatusMapperImpl.getInstance();
    
    private OrderStatusDaoImpl() {
    }

    public static OrderStatusDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<OrderStatus> findAll() throws DaoException {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_STATUSES)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var orderStatus = orderStatusMapper.toEntity(resultSet);
                    orderStatuses.add(orderStatus);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderStatuses;
    }

    @Override
    public boolean insert(OrderStatus orderStatus) throws DaoException {
        return false;
    }

    @Override
    public Optional<OrderStatus> findByName(String statusName) throws DaoException {
        Optional<OrderStatus> statusOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_STATUS_BY_NAME)) {

            preparedStatement.setString(1, statusName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    var orderStatus = orderStatusMapper.toEntity(resultSet);
                    statusOptional = Optional.of(orderStatus);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return statusOptional;
    }
}
