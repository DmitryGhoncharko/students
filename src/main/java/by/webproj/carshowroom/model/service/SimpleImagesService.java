package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.Image;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.ImageDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class SimpleImagesService implements ImagesService {
    private final ImageDao imageDao;

    @Override
    public long addImage(long userId) throws ServiceError {
        try {
            return imageDao.add(userId);
        } catch (DaoException e) {
            log.error("Cannot add image", e);
            throw new ServiceError("Cannot add image", e);
        }
    }

    @Override
    public List<Image> getByUserId(long userId) throws ServiceError {
        try {
            return imageDao.getByUserId(userId);
        } catch (DaoException e) {
            log.error("Cannot get images by userId", e);
            throw new ServiceError("Cannot get images by userId", e);
        }
    }

    @Override
    public boolean deleteByImageId(long imageId) throws ServiceError {
        try {
            return imageDao.deleteByImageId(imageId);
        } catch (DaoException e) {
            log.error("Cannot delete image by imageId", e);
            throw new ServiceError("Cannot delete image by imageId", e);
        }
    }
}
