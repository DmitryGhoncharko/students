package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.CarRepair;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;

public interface CarRepairDao {
    boolean add(CarRepair carRepair) throws DaoException;

    boolean update(long carRepairId) throws DaoException;

    List<CarRepair> selectByUserId(long userId) throws DaoException;

    List<CarRepair> selectByUserIdWhereStatusComplete(long userId) throws DaoException;

    List<CarRepair> getAll() throws DaoException;
}
