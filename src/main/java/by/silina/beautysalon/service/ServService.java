package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.Serv;

import java.util.List;
import java.util.Optional;

public interface ServService {
    List<Serv> findComplexServices() throws ServiceException;

    List<Serv> findNotComplexServices() throws ServiceException;

    Optional<Serv> findServiceByName(String name) throws ServiceException;
}
