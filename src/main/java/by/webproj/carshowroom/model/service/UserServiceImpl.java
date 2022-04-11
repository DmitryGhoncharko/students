package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.UserDao;
import by.webproj.carshowroom.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService{
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserValidator userValidator;
    private final UserDao userDao;

    public UserServiceImpl(UserValidator userValidator, UserDao userDao) {
        this.userValidator = userValidator;
        this.userDao = userDao;
    }

    @Override
    public User addUser(User user) {
        try{
            return userDao.addUser(user);
        }catch (DaoException e){
            LOG.error("Cannot add new user, userData: " + user);
            throw new ServiceError("Cannot add new user, userData: " + user);
        }
    }
}
