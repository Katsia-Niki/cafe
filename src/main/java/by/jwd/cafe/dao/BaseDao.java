package by.jwd.cafe.dao;

import by.jwd.cafe.entity.AbstractEntity;
import by.jwd.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao <T extends AbstractEntity>{
    List<T> findAll() throws DaoException;
    boolean add(T t) throws DaoException;
    boolean delete(T t) throws DaoException;
    boolean update(T t) throws DaoException;
}
