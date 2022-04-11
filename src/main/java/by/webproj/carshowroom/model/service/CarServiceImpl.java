package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.CarDao;
import by.webproj.carshowroom.validator.CarValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CarServiceImpl implements CarService {
    private static final Logger LOG = LoggerFactory.getLogger(CarServiceImpl.class);
    private final CarValidator carValidator;
    private final CarDao carDao;

    public CarServiceImpl(CarValidator carValidator, CarDao carDao) {
        this.carValidator = carValidator;
        this.carDao = carDao;
    }

    @Override
    public Car addCar(Car car) {

        try {
            return carDao.addCar(car);
        } catch (DaoException daoException) {
            LOG.error("Cannot add new car  carData: " + car, daoException);
            throw new ServiceError("Cannot add new car  carData: " + car, daoException);
        }

    }

    @Override
    public boolean deleteCar(long carId) {
        try {
            return carDao.deleteCar(carId);
        } catch (DaoException daoException) {
            LOG.error("Cannot delete car by cat id, carId: " + carId, daoException);
            throw new ServiceError("Cannot delete car by cat id, carId: " + carId, daoException);
        }
    }

    @Override
    public Car updateCar(Car car) {
        try {
            return carDao.updateCar(car);
        } catch (DaoException daoException) {
            LOG.error("Cannot updateCar, carData: " + car, daoException);
            throw new ServiceError("Cannot updateCar, carData: " + car, daoException);
        }
    }

    @Override
    public List<Car> getCars() {
        try {
            return carDao.getCars();
        } catch (DaoException daoException) {
            LOG.error("Cannot get all cars", daoException);
            throw new ServiceError("Cannot get all cars", daoException);
        }
    }
}
