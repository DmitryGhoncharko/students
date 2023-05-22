package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Passport;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface PassportDao {
    long add(Long id, String customer, String org, String ruk, String gen, String tex, String ots) throws DaoException;

    boolean remove(Long id) throws DaoException;

    List<Passport> getAllByUserId(Long userId) throws DaoException;

    Optional<Passport>  getById(Long id) throws DaoException;

    boolean update(Long id, String customer, String org, String ruk, String gen, String tex, String ots) throws DaoException;
}
