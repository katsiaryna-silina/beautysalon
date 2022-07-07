package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.AbstractEntity;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {

    public abstract boolean insert(T t) throws DaoException;

    public boolean deleteById(Long id) throws DaoException {
        throw new UnsupportedOperationException("Method delete() is unsupported.");
    }

    public List<T> findAll() throws DaoException {
        throw new UnsupportedOperationException("Method findAll() is unsupported.");
    }

    public boolean update(T t) throws DaoException {
        throw new UnsupportedOperationException("Method update() is unsupported.");
    }
}
