package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.CarRepair;
import by.webproj.carshowroom.entity.Request;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;

public interface RequestDao {
    boolean add(Request request) throws DaoException;

    boolean removeByUserId(long id) throws DaoException;

    List<Request> getAll() throws DaoException;
}
