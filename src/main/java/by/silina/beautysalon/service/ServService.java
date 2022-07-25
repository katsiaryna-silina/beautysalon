package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.ServiceDto;
import by.silina.beautysalon.model.entity.Serv;

import java.util.List;
import java.util.Optional;

/**
 * The ServService class interface.
 *
 * @author Silina Katsiaryna
 */
public interface ServService {

    /**
     * Finds complex services.
     *
     * @return List of Serv. List of services.
     * @throws ServiceException if a service exception occurs.
     */
    List<Serv> findComplexServices() throws ServiceException;

    /**
     * Finds not complex services.
     *
     * @return List of Serv. List of services.
     * @throws ServiceException if a service exception occurs.
     */
    List<Serv> findNotComplexServices() throws ServiceException;

    /**
     * Finds service by its name.
     *
     * @param name String. Service name.
     * @return Optional of Serv
     * @throws ServiceException if a service exception occurs.
     */
    Optional<Serv> findServiceByName(String name) throws ServiceException;

    /**
     * Deletes a service by its id.
     *
     * @param id Long. Service id.
     * @return boolean. True if the service is deleted; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    boolean deleteById(Long id) throws ServiceException;

    /**
     * Updates a service by its id.
     *
     * @param id Long. Service id.
     * @return boolean. True if the service is updated; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    boolean updateById(Long id) throws ServiceException;


    /**
     * Finds number of services.
     *
     * @return long. Number of services.
     * @throws ServiceException if a service exception occurs.
     */
    long findNumberOfServices() throws ServiceException;

    /**
     * Finds paged services.
     *
     * @param fromServiceId           Long. The first service to find.
     * @param numberOfServicesPerPage Integer. Number of services.
     * @return List of ServiceDto
     * @throws ServiceException if a service exception occurs.
     */
    List<ServiceDto> findPagedServiceDtoList(Long fromServiceId, Integer numberOfServicesPerPage) throws ServiceException;
}
