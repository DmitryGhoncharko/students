package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;

public interface UserDao {
   User addUser(User user) throws DaoException;

}
