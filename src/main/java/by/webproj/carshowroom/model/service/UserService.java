package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.User;

import java.util.Optional;

public interface UserService {
    User addUserAsAdmin(String login, String password);

    Optional<User> authenticateIfAdmin(String login, String password);
}
