package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.User;

import java.util.Optional;

public interface UserService {
    User addUser(User user);

    Optional<User> authorizeIfAdmin(String login, String password);
}
