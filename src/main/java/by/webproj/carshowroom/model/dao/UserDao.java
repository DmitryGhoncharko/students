package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;

import java.util.Optional;

public interface UserDao {
   User addUser(User user) throws DaoException;

   Optional<User> findUserByLogin(String login) throws DaoException;
}
