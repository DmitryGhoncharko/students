package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.entity.Favorites;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.FavoritesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class SimpleFavoritesService implements FavoritesService{
    private static final Logger LOG = LoggerFactory.getLogger(SimpleFavoritesService.class);
    private final FavoritesDao favoritesDao;

    public SimpleFavoritesService(FavoritesDao favoritesDao) {
        this.favoritesDao = favoritesDao;
    }

    @Override
    public List<Car> findFavoritesCarByUserId(long userId) {
        try{
            return favoritesDao.findFavoritesCarByUserId(userId);
        }catch (DaoException e){
            LOG.error("Cannot find favorites by id, userId: " + userId, e);
            throw new ServiceError("Cannot find favorites by id, userId: " + userId, e);
        }
    }

    @Override
    public int addToFavorites(long userId, long carId) {
        try{
            return favoritesDao.addToFavorites(userId, carId);
        }catch (DaoException e){
            LOG.error("Cannot add to favorites",e);
            throw new ServiceError("Cannot add to favorites",e);
        }
    }

    @Override
    public Optional<Favorites> findFavoritesById(long id) {
        try{
            return favoritesDao.findFavoritesById(id);
        }catch (DaoException e){
            LOG.error("Cannot find favorites by id",e);
            throw new ServiceError("Cannot find favorites by id",e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        try{
            return favoritesDao.deleteById(id);
        }catch (DaoException e){
            LOG.error("Cannot delete by id",e);
            throw new ServiceError("Cannot delete by id",e);
        }
    }
}
