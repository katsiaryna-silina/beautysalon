package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.DiscountStatus;

import java.util.List;
import java.util.Optional;

public interface DiscountStatusService {
    List<DiscountStatus> findAll() throws ServiceException;

    Optional<DiscountStatus> findMaximum() throws ServiceException;
}
