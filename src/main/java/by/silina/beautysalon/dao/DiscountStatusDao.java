package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.DiscountStatus;

import java.util.Optional;

public interface DiscountStatusDao {
    Optional<DiscountStatus> findMaximum() throws DaoException;
}
