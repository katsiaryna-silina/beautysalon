package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.OrderDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.model.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.dao.TableColumnName.ORDER_ID;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final OrderDaoImpl instance = new OrderDaoImpl();
    private static final String SELECT_ORDER_ID = """
            SELECT NEXTVAL('ORDERS') AS ORDER_ID
            """;
    private static final String INSERT_FEEDBACK = """
            INSERT INTO ORDER_FEEDBACKS (ID) 
            VALUES (?)
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

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insertOrder(OrderFormDto orderFormDto) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            Long orderId = null;

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

                insertFeedback.setLong(1, orderId);

                int rowCountDML = insertFeedback.executeUpdate();

                if (rowCountDML != 1) {
                    connection.rollback();
                    log.error("Feedback id was not inserted. Transaction has been rolled back.");
                    throw new DaoException("Cannot insert feedback id.");
                }

                insertOrder.setLong(1, orderId);
                insertOrder.setObject(2, orderFormDto.getVisitDate());
                insertOrder.setObject(3, orderFormDto.getVisitBeginTime());
                insertOrder.setObject(4, orderFormDto.getVisitEndTime());
                insertOrder.setLong(5, orderFormDto.getUserId());
                insertOrder.setBigDecimal(6, orderFormDto.getPriceWithDiscount());
                insertOrder.setLong(7, orderId);

                rowCountDML = insertOrder.executeUpdate();

                if (rowCountDML != 1) {
                    connection.rollback();
                    log.error("Order was not inserted. Transaction has been rolled back.");
                    throw new DaoException("Cannot insert order.");
                }

                insertOrdersServices.setLong(1, orderId);

                for (Long serviceId : orderFormDto.getServicesIds()) {
                    insertOrdersServices.setLong(2, serviceId);
                    rowCountDML = insertOrdersServices.executeUpdate();

                    if (rowCountDML != 1) {
                        connection.rollback();
                        log.error("ORDERS_SERVICES table was not updated. Transaction has been rolled back.");
                        throw new DaoException("Cannot update ORDERS_SERVICES table.");
                    }
                }

                insertOrdersVisitTimes.setLong(1, orderId);

                for (Long timeSlotId : orderFormDto.getTimeSlotIds()) {
                    insertOrdersVisitTimes.setLong(2, timeSlotId);
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
    public boolean insert(Order order) throws DaoException {
        return false;
    }

    @Override
    public Order update(Order order) throws DaoException {
        return null;
    }
}
