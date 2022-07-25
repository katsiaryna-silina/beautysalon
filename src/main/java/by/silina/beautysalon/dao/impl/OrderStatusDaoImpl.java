package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.OrderStatusDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.OrderStatusMapper;
import by.silina.beautysalon.mapper.impl.OrderStatusMapperImpl;
import by.silina.beautysalon.model.entity.OrderStatus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The OrderStatusDaoImpl class that responsible for getting data of order statuses from datasource.
 *
 * @author Silina Katsiaryna
 */
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
    private static final String SELECT_DEFAULT_ORDER_STATUS = """
            SELECT OS.ID, OS.STATUS, OS.DESCRIPTION, R.ROLE
            FROM ORDER_STATUSES OS
            JOIN USER_ROLES R ON R.ID = OS.ACCESSIBLE_TO_ROLE_ID
            JOIN ORDERS O ON O.ORDER_STATUS_ID = OS.ID
            WHERE O.ORDER_STATUS_ID = DEFAULT(ORDER_STATUS_ID)
            """;
    private final OrderStatusMapper orderStatusMapper = OrderStatusMapperImpl.getInstance();

    /**
     * Initializes a new OrderStatusDaoImpl.
     */
    private OrderStatusDaoImpl() {
    }

    /**
     * Gets the single instance of OrderStatusDaoImpl.
     *
     * @return OrderStatusDaoImpl
     */
    public static OrderStatusDaoImpl getInstance() {
        return instance;
    }

    /**
     * Finds all order statuses.
     *
     * @return List of OrderStatus
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public List<OrderStatus> findAll() throws DaoException {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_ORDER_STATUSES)) {

            try (var resultSet = preparedStatement.executeQuery()) {
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

    /**
     * Inserts an order statuses. For this version it is unsupported method.
     *
     * @param orderStatus OrderStatus. The order status to insert.
     * @return boolean. True if this order status is inserted; false otherwise.
     */
    @Override
    public boolean insert(OrderStatus orderStatus) {
        throw new UnsupportedOperationException("Method insert() is unsupported.");
    }

    /**
     * Finds order status by its name.
     *
     * @param statusName String. The name of status.
     * @return Optional of OrderStatus
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public Optional<OrderStatus> findByName(String statusName) throws DaoException {
        Optional<OrderStatus> statusOptional = Optional.empty();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_ORDER_STATUS_BY_NAME)) {

            preparedStatement.setString(1, statusName);
            try (var resultSet = preparedStatement.executeQuery()) {
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

    /**
     * Finds default order status when order has just created.
     *
     * @return Optional of OrderStatus
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public Optional<OrderStatus> findDefault() throws DaoException {
        Optional<OrderStatus> statusOptional = Optional.empty();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_DEFAULT_ORDER_STATUS)) {

            try (var resultSet = preparedStatement.executeQuery()) {
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
