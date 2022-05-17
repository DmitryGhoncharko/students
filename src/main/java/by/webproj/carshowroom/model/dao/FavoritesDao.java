package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.entity.Favorites;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface FavoritesDao {
    List<Car> findFavoritesCarByUserId(long userId) throws DaoException;

    int addToFavorites(long userId, long carId) throws DaoException;

    Optional<Favorites> findFavoritesById(long id) throws DaoException;

    boolean deleteById(long id) throws DaoException;
}
