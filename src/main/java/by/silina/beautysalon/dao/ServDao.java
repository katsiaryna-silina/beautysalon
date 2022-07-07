package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.Serv;

import java.util.List;
import java.util.Optional;

public interface ServDao {
    List<Serv> findServices(boolean complex) throws DaoException;

    Optional<Serv> findServiceByName(String name) throws DaoException;

    long findNumberOfServices() throws DaoException;

    List<Serv> findPagedServiceList(Long fromServiceId, Integer numberOfServicesPerPage) throws DaoException;

    boolean updateById(Long id) throws DaoException;
}
