package by.webproj.carshowroom.validator;

import by.webproj.carshowroom.entity.Car;

import java.sql.Blob;

public interface CarValidator {
    boolean validateCarData(Car car);
}
