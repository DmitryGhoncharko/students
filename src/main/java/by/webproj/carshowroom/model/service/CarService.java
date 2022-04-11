package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;

public interface CarService {
    Car addCar(Car car) ;
    boolean deleteCar(long carId) ;
    Car updateCar(Car car) ;
    List<Car> getCars();
}
