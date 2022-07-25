package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.AbstractEntity;

import java.util.List;

/**
 * The BaseDao class.
 *
 * @author Silina Katsiaryna
 */
public abstract class BaseDao<T extends AbstractEntity> {

    /**
     * Inserts entity to the datasource.
     *
     * @param t AbstractEntity
     * @return boolean. True if it was successful; false otherwise.
     * @throws DaoException the dao exception
     */
    public abstract boolean insert(T t) throws DaoException;

    /**
     * Deletes entity by its id.
     *
     * @param id Long. Id of the entity.
     * @return boolean. True if it was successful; false otherwise.
     * @throws DaoException the dao exception
     */
    public boolean deleteById(Long id) throws DaoException {
        throw new UnsupportedOperationException("Method delete() is unsupported.");
    }

    /**
     * Finds all entities. For this version it is basically unsupported method, but can be overridden.
     *
     * @return List of T where T is a class that extends AbstractEntity.
     * @throws DaoException the dao exception
     */
    public List<T> findAll() throws DaoException {
        throw new UnsupportedOperationException("Method findAll() is unsupported.");
    }

    /**
     * Updates the entity. For this version it is basically unsupported method, but can be overridden.
     *
     * @param t AbstractEntity
     * @return boolean. True if it was successful; false otherwise.
     * @throws DaoException the dao exception
     */
    public boolean update(T t) throws DaoException {
        throw new UnsupportedOperationException("Method update() is unsupported.");
    }
}
