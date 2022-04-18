package by.webproj.carshowroom.validator;

import by.webproj.carshowroom.entity.Car;

public class SimpleCarValidator implements CarValidator{
    @Override
    public boolean validateCarData(Car car) {
        if(car.getCarName()!=null && car.getCarDescription() !=null ){
           return car.getCarName().length()>0 && car.getCarName().length()<=100 && car.getCarDescription().length()>0;
        }
        return false;
    }
}
