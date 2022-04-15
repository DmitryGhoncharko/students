package by.webproj.carshowroom.validator;

import by.webproj.carshowroom.entity.User;

public interface UserValidator {
    boolean validateUserDataByUserObject(User user);

    boolean validateUserDataByLoginAndPassword(String login, String password);
}
