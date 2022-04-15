package by.webproj.carshowroom.validator;


import by.webproj.carshowroom.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleUserValidator implements UserValidator {

    private static final String LOGIN_REGEXP = "^[a-zA-Z0-9]{6,100}$";

    private static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,100}$";


    @Override
    public boolean validateUserDataByUserObject(User user) {
        return validateUserDataByLoginAndPassword(user.getUserLogin(), user.getUserPassword());
    }

    @Override
    public boolean validateUserDataByLoginAndPassword(String login, String password) {
        if (login != null && password != null) {
            Pattern pattern = Pattern.compile(LOGIN_REGEXP);
            Matcher userLoginMather = pattern.matcher(login);
            final boolean userLoginIsValid = userLoginMather.find();
            pattern = Pattern.compile(PASSWORD_REGEXP);
            Matcher userPasswordMatcher = pattern.matcher(password);
            final boolean userPasswordIsValid = userPasswordMatcher.find();
            return userLoginIsValid && userPasswordIsValid;
        }
        return false;
    }
}
