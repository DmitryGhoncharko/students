package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Image;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;

public interface ImageDao {
    long add(long userId) throws DaoException;

    List<Image> getByUserId(long userId) throws DaoException;

    boolean deleteByImageId(long imageId) throws DaoException;
}
