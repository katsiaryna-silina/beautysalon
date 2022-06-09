package by.silina.beautysalon.dao;

import by.silina.beautysalon.entity.AbstractEntity;
import by.silina.beautysalon.exception.DaoException;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;

    public abstract boolean delete(T t) throws DaoException;

    public abstract List<T> findAll() throws DaoException;

    public abstract T update(T t) throws DaoException;
}
