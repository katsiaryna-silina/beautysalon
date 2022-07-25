package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.ServDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.ServiceMapper;
import by.silina.beautysalon.mapper.impl.ServiceMapperImpl;
import by.silina.beautysalon.model.dto.ServiceDto;
import by.silina.beautysalon.model.entity.Serv;
import by.silina.beautysalon.service.ServService;

import java.util.List;
import java.util.Optional;

/**
 * The ServServiceImpl class that responsible for processing Serv.
 *
 * @author Silina Katsiaryna
 */
public class ServServiceImpl implements ServService {
    private static final ServServiceImpl instance = new ServServiceImpl();
    private static final ServDaoImpl serviceDao = ServDaoImpl.getInstance();
    private final ServiceMapper serviceMapper = ServiceMapperImpl.getInstance();

    /**
     * Initializes a new ServServiceImpl.
     */
    private ServServiceImpl() {
    }

    /**
     * Gets the single instance of ServServiceImpl.
     *
     * @return ServServiceImpl
     */
    public static ServServiceImpl getInstance() {
        return instance;
    }


    /**
     * Finds complex services.
     *
     * @return List of Serv. List of services.
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public List<Serv> findComplexServices() throws ServiceException {
        try {
            return serviceDao.findServices(true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds not complex services.
     *
     * @return List of Serv. List of services.
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public List<Serv> findNotComplexServices() throws ServiceException {
        try {
            return serviceDao.findServices(false);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds service by its name.
     *
     * @param name String. Service name.
     * @return Optional of Serv
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public Optional<Serv> findServiceByName(String name) throws ServiceException {
        try {
            return serviceDao.findServiceByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes a service by its id.
     *
     * @param id Long. Service id.
     * @return boolean. True if the service is deleted; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            return serviceDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Updates a service by its id.
     *
     * @param id Long. Service id.
     * @return boolean. True if the service is updated; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public boolean updateById(Long id) throws ServiceException {
        try {
            return serviceDao.updateById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds number of services.
     *
     * @return long. Number of services.
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public long findNumberOfServices() throws ServiceException {
        try {
            return serviceDao.findNumberOfServices();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * Finds paged services.
     *
     * @param fromServiceId           Long. The first service to find.
     * @param numberOfServicesPerPage Integer. Number of services.
     * @return List of ServiceDto
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public List<ServiceDto> findPagedServiceDtoList(Long fromServiceId, Integer numberOfServicesPerPage) throws ServiceException {
        try {
            List<Serv> serviceDtoList = serviceDao.findPagedServiceList(fromServiceId, numberOfServicesPerPage);
            return serviceDtoList.stream()
                    .map(serviceMapper::toDto)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
