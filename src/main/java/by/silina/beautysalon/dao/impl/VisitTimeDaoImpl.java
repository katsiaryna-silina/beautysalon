package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.VisitTimeDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.VisitTimeMapper;
import by.silina.beautysalon.mapper.impl.VisitTimeMapperImpl;
import by.silina.beautysalon.model.entity.Order;
import by.silina.beautysalon.model.entity.VisitTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VisitTimeDaoImpl extends BaseDao<Order> implements VisitTimeDao {
    private static final VisitTimeDaoImpl instance = new VisitTimeDaoImpl();
    private static final String SELECT_FREE_VISIT_TIMES = """
            SELECT VT.ID, VT.BEGIN_TIME, VT.END_TIME
            FROM VISIT_TIMES VT
            WHERE VT.IS_DEPRECATED = 'N'
              AND NOT EXISTS (SELECT NULL
                              FROM ORDERS_VISIT_TIMES OVT
                              JOIN ORDERS O ON O.ID = OVT.ORDER_ID
                              WHERE VT.ID = OVT.VISIT_TIME_ID
                                AND DATE(O.VISIT_DATE) = STR_TO_DATE(?, '%d-%m-%Y'))
            """;
    private static final String DD_MM_YYYY = "dd-MM-yyyy";
    private final VisitTimeMapper visitTimeMapper = VisitTimeMapperImpl.getInstance();

    private VisitTimeDaoImpl() {
    }

    public static VisitTimeDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<VisitTime> findFreeVisitTimeSlots(LocalDate visitDate) throws DaoException {
        List<VisitTime> visitTimes = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FREE_VISIT_TIMES)) {

            preparedStatement.setString(1, visitDate.format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    VisitTime visitTime = visitTimeMapper.toEntity(resultSet);
                    visitTimes.add(visitTime);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return visitTimes;
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
