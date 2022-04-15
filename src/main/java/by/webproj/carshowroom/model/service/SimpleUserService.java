package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.Role;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.UserDao;
import by.webproj.carshowroom.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class SimpleUserService implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserService.class);
    private final UserValidator userValidator;
    private final UserDao userDao;

    public SimpleUserService(UserValidator userValidator, UserDao userDao) {
        this.userValidator = userValidator;
        this.userDao = userDao;
    }

    @Override
    public User addUser(User user) {
        try {
            return userDao.addUser(user);
        } catch (DaoException e) {
            LOG.error("Cannot add new user, userData: " + user);
            throw new ServiceError("Cannot add new user, userData: " + user);
        }
    }

    @Override
    public Optional<User> authorizeIfAdmin(String login, String password) {
        try {
            final Optional<User> userFromDB = userDao.findUserByLogin(login);
            if (userFromDB.isPresent()) {
                final User userInstance = userFromDB.get();
                if (userInstance.getUserPassword().equals(password) && userInstance.getUserRole().equals(Role.ADMIN)) {
                    return userFromDB;
                }
            }
        } catch (DaoException daoException) {
            LOG.error("Cannot authorize user, userLogin: " + login + " userPassword :" + password, daoException);
            throw new ServiceError("Cannot authorize user, userLogin: " + login + " userPassword :" + password);
        }
        return Optional.empty();
    }
}
