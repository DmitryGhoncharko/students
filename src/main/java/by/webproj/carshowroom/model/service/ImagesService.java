package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.Image;
import by.webproj.carshowroom.exception.ServiceError;

import java.util.List;

public interface ImagesService {
    long addImage(long imageId) throws ServiceError;

    List<Image> getByUserId(long userId) throws ServiceError;

    boolean deleteByImageId(long userId) throws ServiceError;
}
