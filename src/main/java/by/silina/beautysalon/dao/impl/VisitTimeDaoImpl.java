package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.VisitTimeDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.VisitTimeMapper;
import by.silina.beautysalon.mapper.impl.VisitTimeMapperImpl;
import by.silina.beautysalon.model.entity.VisitTime;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The VisitTimeDaoImpl class that responsible for getting data of visit time from datasource.
 *
 * @author Silina Katsiaryna
 */
public class VisitTimeDaoImpl extends BaseDao<VisitTime> implements VisitTimeDao {
    private static final VisitTimeDaoImpl instance = new VisitTimeDaoImpl();
    private static final String SELECT_FREE_VISIT_TIMES = """
            SELECT VT.ID, VT.BEGIN_TIME, VT.END_TIME
            FROM VISIT_TIMES VT
            WHERE VT.IS_DEPRECATED = 'N'
              AND NOT EXISTS (SELECT NULL
                              FROM ORDERS_VISIT_TIMES OVT
                              JOIN ORDERS O ON O.ID = OVT.ORDER_ID
                              JOIN ORDER_STATUSES OS ON OS.ID = O.ORDER_STATUS_ID
                              WHERE VT.ID = OVT.VISIT_TIME_ID
                                AND DATE(O.VISIT_DATE) = STR_TO_DATE(?, '%d-%m-%Y')
                                AND (OS.STATUS <> 'declined by admin' OR  'declined by client')
                              )
            """;
    private static final String DD_MM_YYYY = "dd-MM-yyyy";
    private final VisitTimeMapper visitTimeMapper = VisitTimeMapperImpl.getInstance();

    /**
     * Initializes a new VisitTimeDaoImpl.
     */
    private VisitTimeDaoImpl() {
    }

    /**
     * Gets the single instance of VisitTimeDaoImpl.
     *
     * @return OrderDaoImpl
     */
    public static VisitTimeDaoImpl getInstance() {
        return instance;
    }

    /**
     * Finds free visit time slots for the date.
     *
     * @param visitDate LocalDate. Date of the visit.
     * @return List of VisitTime
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public List<VisitTime> findFreeVisitTimeSlots(LocalDate visitDate) throws DaoException {
        List<VisitTime> visitTimes = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_FREE_VISIT_TIMES)) {

            preparedStatement.setString(1, visitDate.format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var visitTime = visitTimeMapper.toEntity(resultSet);
                    visitTimes.add(visitTime);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return visitTimes;
    }

    /**
     * Inserts a new visit time. For this version it is unsupported method.
     *
     * @param visitTime VisitTime. The visit time to insert.
     * @return boolean. True if this visit time is inserted; false otherwise.
     */
    @Override
    public boolean insert(VisitTime visitTime) {
        throw new UnsupportedOperationException("Method insert() is unsupported.");
    }
}
