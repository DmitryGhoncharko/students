package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.entity.Favorites;

import java.util.List;
import java.util.Optional;

public interface FavoritesService {
    List<Car> findFavoritesCarByUserId(long userId);

    int addToFavorites(long userId, long carId);

    Optional<Favorites> findFavoritesById(long id);

    boolean deleteById(long id);
}
