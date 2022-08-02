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

import static by.silina.beautysalon.dao.TableColumnName.COUNTER;

/**
 * The VisitTimeDaoImpl class that responsible for getting data of visit time from datasource.
 *
 * @author Silina Katsiaryna
 */
public class VisitTimeDaoImpl extends BaseDao<VisitTime> implements VisitTimeDao {
    private static final VisitTimeDaoImpl instance = new VisitTimeDaoImpl(ConnectionPool.getInstance());
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
    private static final String CHECK_IF_VISIT_TIME_SLOT_FREE_BEGINNING = """
            SELECT COUNT(VT.ID) COUNTER
            FROM VISIT_TIMES VT
            WHERE VT.IS_DEPRECATED = 'N'
              AND VT.ID IN (
            """;
    private static final String CHECK_IF_VISIT_TIME_SLOT_FREE_END = """
            )
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
    private static final String SEPARATOR = ",";
    private static final String QUESTION_MARK = "?";
    private final VisitTimeMapper visitTimeMapper = VisitTimeMapperImpl.getInstance();
    private final ConnectionPool connectionPool;

    /**
     * Initializes a new VisitTimeDaoImpl.
     */
    private VisitTimeDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
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
        try (var connection = connectionPool.getConnection();
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
     * Checks if visit time slots free for the date.
     *
     * @param visitDate    LocalDate. Date of the visit.
     * @param visitTimeIds List of VisitTime id.
     * @return boolean.  True if time slots are free; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public boolean isVisitTimeSlotFree(LocalDate visitDate, List<Long> visitTimeIds) throws DaoException {
        int counter = -1;
        var timeSlotsNumber = visitTimeIds.size();

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append(CHECK_IF_VISIT_TIME_SLOT_FREE_BEGINNING);

        for (int i = 0; i < timeSlotsNumber; i++) {
            sqlQuery.append(QUESTION_MARK)
                    .append(SEPARATOR);
        }
        if (sqlQuery.length() > 0) {
            sqlQuery.deleteCharAt(sqlQuery.lastIndexOf(SEPARATOR));
        }
        sqlQuery.append(CHECK_IF_VISIT_TIME_SLOT_FREE_END);

        try (var connection = connectionPool.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery.toString())) {

            for (int i = 0; i < timeSlotsNumber; i++) {
                preparedStatement.setLong(i + 1, visitTimeIds.get(i));
            }

            preparedStatement.setString(timeSlotsNumber + 1, visitDate.format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    counter = resultSet.getInt(COUNTER);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return counter == timeSlotsNumber;
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
