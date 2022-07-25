package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.Serv;

import java.util.List;
import java.util.Optional;

/**
 * The DiscountStatusDao interface.
 *
 * @author Silina Katsiaryna
 */
public interface ServDao {

    /**
     * Finds all complex or not complex services.
     *
     * @param complex complex. True if needed to find complex services; false otherwise.
     * @return List of Serv. List of services.
     * @throws DaoException if a dao exception occurs.
     */
    List<Serv> findServices(boolean complex) throws DaoException;

    /**
     * Finds service by its name.
     *
     * @param name String. The name of service.
     * @return Optional of Serv
     * @throws DaoException if a dao exception occurs.
     */
    Optional<Serv> findServiceByName(String name) throws DaoException;

    /**
     * Finds number of services.
     *
     * @return long. Number of services.
     * @throws DaoException if a dao exception occurs.
     */
    long findNumberOfServices() throws DaoException;

    /**
     * Finds paged services.
     *
     * @param fromServiceId           Long. The first service to find.
     * @param numberOfServicesPerPage Integer. Number of services.
     * @return List of Serv. List of found services.
     * @throws DaoException if a dao exception occurs.
     */
    List<Serv> findPagedServiceList(Long fromServiceId, Integer numberOfServicesPerPage) throws DaoException;

    /**
     * Updates service status to not deprecated by service id.
     *
     * @param id Long. The id of service.
     * @return boolean. True if this service is updated; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    boolean updateById(Long id) throws DaoException;
}
