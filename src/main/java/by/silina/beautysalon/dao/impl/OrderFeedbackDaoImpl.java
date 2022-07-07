package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.OrderFeedbackDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.OrderFeedbackMapper;
import by.silina.beautysalon.mapper.impl.OrderFeedbackMapperImpl;
import by.silina.beautysalon.model.entity.OrderFeedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderFeedbackDaoImpl extends BaseDao<OrderFeedback> implements OrderFeedbackDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final OrderFeedbackDaoImpl instance = new OrderFeedbackDaoImpl();
    private static final String UPDATE_FEEDBACK = """
            UPDATE ORDER_FEEDBACKS
            SET MARK = ?, FEEDBACK = ?, IS_VERIFIED = 'N'
            WHERE ID = ?
            """;
    private static final String SELECT_ORDER_FEEDBACK_BY_ID = """
            SELECT ID, MARK, FEEDBACK, IS_VERIFIED
            FROM ORDER_FEEDBACKS
            WHERE ID = ?
            """;
    private final OrderFeedbackMapper feedbackMapper = OrderFeedbackMapperImpl.getInstance();

    private OrderFeedbackDaoImpl() {
    }

    public static OrderFeedbackDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(OrderFeedback orderFeedback) throws DaoException {
        throw new UnsupportedOperationException("Method insert() is unsupported.");
    }

    @Override
    public boolean update(OrderFeedback orderFeedback) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FEEDBACK)) {
                var feedbackId = orderFeedback.getId();

                preparedStatement.setByte(1, orderFeedback.getMark());
                preparedStatement.setString(2, orderFeedback.getFeedback());
                preparedStatement.setLong(3, orderFeedback.getId());

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("Order feedback with id={} was updated.", feedbackId);
                    return true;
                } else {
                    connection.rollback();
                    log.debug("Order feedback with id={} was not updated. Transaction has being rolled back", feedbackId);
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
    public Optional<OrderFeedback> findById(Long id) throws DaoException {
        Optional<OrderFeedback> optionalFeedback = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_FEEDBACK_BY_ID)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    OrderFeedback orderFeedback = feedbackMapper.toEntity(resultSet);
                    optionalFeedback = Optional.of(orderFeedback);
                }
            }
            return optionalFeedback;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
