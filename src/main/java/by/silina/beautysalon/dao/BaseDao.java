package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.AbstractEntity;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;

    public boolean delete(T t) throws DaoException {
        throw new UnsupportedOperationException("delete unsupported");
    }

    public List<T> findAll() throws DaoException {
        throw new UnsupportedOperationException("findAll() unsupported");
    }

    public abstract T update(T t) throws DaoException;
}
