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

public class ServServiceImpl implements ServService {
    private static final ServServiceImpl instance = new ServServiceImpl();
    private static final ServDaoImpl serviceDao = ServDaoImpl.getInstance();
    private final ServiceMapper serviceMapper = ServiceMapperImpl.getInstance();

    private ServServiceImpl() {
    }

    public static ServServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Serv> findComplexServices() throws ServiceException {
        try {
            return serviceDao.findServices(true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serv> findNotComplexServices() throws ServiceException {
        try {
            return serviceDao.findServices(false);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Serv> findServiceByName(String name) throws ServiceException {
        try {
            return serviceDao.findServiceByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            return serviceDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateById(Long id) throws ServiceException {
        try {
            return serviceDao.updateById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long findNumberOfServices() throws ServiceException {
        try {
            return serviceDao.findNumberOfServices();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

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
