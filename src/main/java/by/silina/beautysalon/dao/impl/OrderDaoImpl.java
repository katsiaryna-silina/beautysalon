package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.OrderDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.OrderMapper;
import by.silina.beautysalon.mapper.impl.OrderMapperImpl;
import by.silina.beautysalon.model.entity.Order;
import by.silina.beautysalon.model.entity.Serv;
import by.silina.beautysalon.model.entity.VisitTime;
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

import static by.silina.beautysalon.dao.TableColumnName.*;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final OrderDaoImpl instance = new OrderDaoImpl();
    private static final String SELECT_ORDER_ID = """
            SELECT NEXTVAL('ORDERS') AS ORDER_ID
            """;
    private static final String INSERT_FEEDBACK = """
            INSERT INTO ORDER_FEEDBACKS (ID, USER_ID) 
            VALUES (?, ?)
            """;
    private static final String INSERT_ORDER = """
            INSERT INTO ORDERS (ID, VISIT_DATE, VISIT_BEGIN_TIME, VISIT_END_TIME, USER_ID, PRICE_WITH_DISCOUNT, FEEDBACK_ID)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String INSERT_ORDERS_SERVICES = """
            INSERT INTO ORDERS_SERVICES (ORDER_ID, SERVICE_ID)
            VALUES (?, ?)
            """;
    private static final String INSERT_ORDERS_VISIT_TIMES = """
            INSERT INTO ORDERS_VISIT_TIMES (ORDER_ID, VISIT_TIME_ID)
            VALUES (?, ?)
            """;
    private static final String SELECT_NUMBER_OF_ORDERS = """
            SELECT COUNT(ID)
            FROM ORDERS;
            """;
    private static final String SELECT_NUMBER_OF_USER_ORDERS = """
            SELECT COUNT(ID)
            FROM ORDERS
            WHERE USER_ID = ?
            """;
    private static final String SELECT_PAGED_ORDERS = """
            SELECT O.ID, O.ORDER_DATE_TIME, O.VISIT_DATE, O.VISIT_BEGIN_TIME, O.VISIT_END_TIME, O.PRICE_WITH_DISCOUNT,
                   O_ST.STATUS, O_ST.DESCRIPTION,
                   U.USERNAME, U.FIRST_NAME, U.LAST_NAME, U.EMAIL, U.PHONE_NUMBER,
                   S.NAME AS SERVICE_NAME
            FROM ORDERS O
            JOIN ORDER_STATUSES O_ST ON O_ST.ID = O.ORDER_STATUS_ID
            JOIN USERS U ON U.ID = O.USER_ID
            JOIN ORDERS_SERVICES O_SERV ON O_SERV.ORDER_ID = O.ID
            JOIN SERVICES S ON S.ID = O_SERV.SERVICE_ID
            WHERE O.ID > ? LIMIT ?
            """;
    private static final String SELECT_ORDER_BY_ID = """
            SELECT O.ID, O.ORDER_DATE_TIME, O.VISIT_DATE, O.VISIT_BEGIN_TIME, O.VISIT_END_TIME, O.PRICE_WITH_DISCOUNT,
                   O_ST.STATUS, O_ST.DESCRIPTION,
                   U.USERNAME, U.FIRST_NAME, U.LAST_NAME, U.EMAIL, U.PHONE_NUMBER,
                   S.NAME AS SERVICE_NAME
            FROM ORDERS O
            JOIN ORDER_STATUSES O_ST ON O_ST.ID = O.ORDER_STATUS_ID
            JOIN USERS U ON U.ID = O.USER_ID
            JOIN ORDERS_SERVICES O_SERV ON O_SERV.ORDER_ID = O.ID
            JOIN SERVICES S ON S.ID = O_SERV.SERVICE_ID
            WHERE O.ID  = ?
            """;
    private static final String UPDATE_ORDER_STATUS = """
            UPDATE ORDERS
            SET ORDER_STATUS_ID = (SELECT ID
                                   FROM ORDER_STATUSES
                                   WHERE STATUS = ?)
            WHERE ID = ?            
            """;
    private static final String SELECT_PAGED_ORDERS_FOR_USER = """
             SELECT O.ID, O.ORDER_DATE_TIME, O.VISIT_DATE, O.VISIT_BEGIN_TIME, O.VISIT_END_TIME, O.PRICE_WITH_DISCOUNT,
                   O_ST.STATUS, O_ST.DESCRIPTION,
                   U.USERNAME, U.FIRST_NAME, U.LAST_NAME, U.EMAIL, U.PHONE_NUMBER,
                   S.NAME AS SERVICE_NAME
            FROM ORDERS O
            JOIN ORDER_STATUSES O_ST ON O_ST.ID = O.ORDER_STATUS_ID
            JOIN USERS U ON U.ID = O.USER_ID
            JOIN ORDERS_SERVICES O_SERV ON O_SERV.ORDER_ID = O.ID
            JOIN SERVICES S ON S.ID = O_SERV.SERVICE_ID
            WHERE O.USER_ID = ? AND O.ID > ? LIMIT ?
            """;
    private final OrderMapper orderMapper = OrderMapperImpl.getInstance();

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(Order order) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            Long orderId = null;
            Long userId = order.getUser().getId();
            Long feedbackId = null;

            try (PreparedStatement selectOrderId = connection.prepareStatement(SELECT_ORDER_ID);
                 PreparedStatement insertFeedback = connection.prepareStatement(INSERT_FEEDBACK);
                 PreparedStatement insertOrder = connection.prepareStatement(INSERT_ORDER);
                 PreparedStatement insertOrdersServices = connection.prepareStatement(INSERT_ORDERS_SERVICES);
                 PreparedStatement insertOrdersVisitTimes = connection.prepareStatement(INSERT_ORDERS_VISIT_TIMES)) {

                try (ResultSet resultSet = selectOrderId.executeQuery()) {
                    if (resultSet.next()) {
                        orderId = resultSet.getLong(ORDER_ID);
                    }
                }

                if (orderId == null) {
                    throw new DaoException("Cannot get new order id.");
                }
                feedbackId = orderId;

                insertFeedback.setLong(1, feedbackId);
                insertFeedback.setLong(2, userId);

                int rowCountDML = insertFeedback.executeUpdate();

                if (rowCountDML != 1) {
                    connection.rollback();
                    log.error("Feedback id was not inserted. Transaction has been rolled back.");
                    throw new DaoException("Cannot insert feedback id.");
                }

                insertOrder.setLong(1, orderId);
                insertOrder.setObject(2, order.getVisitDate());
                insertOrder.setObject(3, order.getVisitBeginTime());
                insertOrder.setObject(4, order.getVisitEndTime());
                insertOrder.setLong(5, userId);
                insertOrder.setBigDecimal(6, order.getPriceWithDiscount());
                insertOrder.setLong(7, orderId);

                rowCountDML = insertOrder.executeUpdate();

                if (rowCountDML != 1) {
                    connection.rollback();
                    log.error("Order was not inserted. Transaction has been rolled back.");
                    throw new DaoException("Cannot insert order.");
                }

                insertOrdersServices.setLong(1, orderId);

                for (Serv service : order.getServices()) {
                    insertOrdersServices.setLong(2, service.getId());
                    rowCountDML = insertOrdersServices.executeUpdate();

                    if (rowCountDML != 1) {
                        connection.rollback();
                        log.error("ORDERS_SERVICES table was not updated. Transaction has been rolled back.");
                        throw new DaoException("Cannot update ORDERS_SERVICES table.");
                    }
                }

                insertOrdersVisitTimes.setLong(1, orderId);

                for (VisitTime timeSlot : order.getTimeSlots()) {
                    insertOrdersVisitTimes.setLong(2, timeSlot.getId());
                    rowCountDML = insertOrdersVisitTimes.executeUpdate();

                    if (rowCountDML != 1) {
                        connection.rollback();
                        log.error("ORDERS_VISIT_TIMES table was not updated. Transaction has been rolled back.");
                        throw new DaoException("Cannot update ORDERS_VISIT_TIMES table.");
                    }
                }
            }
            connection.commit();
            return true;
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
    public Optional<Order> findById(Long orderId) throws DaoException {
        Optional<Order> orderOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {

            preparedStatement.setLong(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Order order = null;
                while (resultSet.next()) {
                    if (order == null) {
                        order = orderMapper.toEntity(resultSet);
                    } else {
                        List<Serv> services = order.getServices();
                        services.add(Serv.builder()
                                .name(resultSet.getString(SERVICE_NAME))
                                .build());
                    }
                }
                if (order != null) {
                    orderOptional = Optional.of(order);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderOptional;
    }


    @Override
    public long findNumberOfOrders() throws DaoException {
        long numberOfOrders = 0L;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NUMBER_OF_ORDERS)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    numberOfOrders = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return numberOfOrders;
    }

    @Override
    public long findNumberOfOrders(Long userId) throws DaoException {
        long numberOfOrders = 0L;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NUMBER_OF_USER_ORDERS)) {

            preparedStatement.setLong(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    numberOfOrders = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return numberOfOrders;
    }

    @Override
    public List<Order> findPagedOrders(Long fromOrderId, Integer numberOfOrders) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAGED_ORDERS)) {

            preparedStatement.setLong(1, fromOrderId);
            preparedStatement.setInt(2, numberOfOrders);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                long orderId = 0;
                Order order = null;
                while (resultSet.next()) {
                    long nextOrderId = resultSet.getLong(ID);
                    if (orderId != nextOrderId) {
                        order = orderMapper.toEntity(resultSet);
                        orders.add(order);
                        orderId = nextOrderId;
                    } else {
                        if (order != null) {
                            List<Serv> services = order.getServices();
                            services.add(Serv.builder()
                                    .name(resultSet.getString(SERVICE_NAME))
                                    .build());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findPagedOrders(Long fromOrderId, Integer numberOfOrders, Long userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAGED_ORDERS_FOR_USER)) {

            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, fromOrderId);
            preparedStatement.setInt(3, numberOfOrders);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                long orderId = 0;
                Order order = null;
                while (resultSet.next()) {
                    long nextOrderId = resultSet.getLong(ID);
                    if (orderId != nextOrderId) {
                        order = orderMapper.toEntity(resultSet);
                        orders.add(order);
                        orderId = nextOrderId;
                    } else {
                        if (order != null) {
                            List<Serv> services = order.getServices();
                            services.add(Serv.builder()
                                    .name(resultSet.getString(SERVICE_NAME))
                                    .build());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public boolean changeStatus(Long orderId, String statusName) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
                preparedStatement.setString(1, statusName);
                preparedStatement.setLong(2, orderId);

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("Order's status with id={} was updated.", orderId);
                    return true;
                } else {
                    connection.rollback();
                    log.debug("Order's status with id={} was not updated. Transaction has being rolled back", orderId);
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
}
