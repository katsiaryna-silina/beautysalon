package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.DiscountStatusDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.DiscountStatusMapper;
import by.silina.beautysalon.mapper.impl.DiscountStatusMapperImpl;
import by.silina.beautysalon.model.entity.DiscountStatus;
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

import static by.silina.beautysalon.dao.TableColumnName.STATUS;

public class DiscountStatusDaoImpl extends BaseDao<DiscountStatus> implements DiscountStatusDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final DiscountStatusDaoImpl instance = new DiscountStatusDaoImpl();
    private static final String SELECT_ALL_DISCOUNT_STATUSES = """
            SELECT ID, STATUS, DISCOUNT
            FROM DISCOUNT_STATUSES
            """;
    private static final String SELECT_MAXIMUM_DISCOUNT_STATUS = """
            SELECT STATUS
            FROM DISCOUNT_STATUSES
            WHERE DISCOUNT = (
                SELECT MAX(DISCOUNT)
                FROM DISCOUNT_STATUSES
                )
            """;
    private final DiscountStatusMapper discountStatusMapper = DiscountStatusMapperImpl.getInstance();

    private DiscountStatusDaoImpl() {
    }

    public static DiscountStatusDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<DiscountStatus> findAll() throws DaoException {
        List<DiscountStatus> discountStatuses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DISCOUNT_STATUSES)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var discountStatus = discountStatusMapper.toEntity(resultSet);
                    discountStatuses.add(discountStatus);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return discountStatuses;
    }

    @Override
    public boolean insert(DiscountStatus discountStatus) {
        throw new UnsupportedOperationException("Method insert() is unsupported.");
    }

    @Override
    public Optional<DiscountStatus> findMaximum() throws DaoException {
        Optional<DiscountStatus> optionalDiscountStatus = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAXIMUM_DISCOUNT_STATUS)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    var discountStatus = DiscountStatus.builder()
                            .status(resultSet.getString(STATUS))
                            .build();
                    optionalDiscountStatus = Optional.of(discountStatus);
                    log.debug("Discount status was found.");
                } else {
                    log.debug("Discount status was not found.");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalDiscountStatus;
    }
}
