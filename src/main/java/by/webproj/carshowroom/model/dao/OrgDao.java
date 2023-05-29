package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Org;
import by.webproj.carshowroom.exception.DaoException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrgDao {
    boolean add(String name, String desc, Date date, Long userId) throws DaoException;

    boolean remove(Long id) throws DaoException;

    List<Org> getAllByUserId(Long userId) throws DaoException;

    Optional<Org> getById(Long id) throws DaoException;
    boolean update(Long id, String name, String desc, Date date) throws DaoException;
}
