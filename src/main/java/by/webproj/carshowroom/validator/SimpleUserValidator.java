package by.webproj.carshowroom.validator;


import by.webproj.carshowroom.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleUserValidator implements UserValidator {

    private static final String LOGIN_REGEXP = "^[a-zA-Z0-9]{6,100}$";

    private static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,100}$";


    @Override
    public boolean validateUserData(User user) {
        if (user.getUserLogin() != null && user.getUserPassword() != null && user.getUserRole() != null) {
            Pattern pattern = Pattern.compile(LOGIN_REGEXP);
            Matcher matcher = pattern.matcher(user.getUserLogin());
            final boolean userLoginIsValid = matcher.find();
            pattern = Pattern.compile(PASSWORD_REGEXP);
            Matcher matcher1 = pattern.matcher(user.getUserPassword());
            final boolean userPasswordIsValid = matcher.find();
            return userLoginIsValid && userPasswordIsValid;
        }
        return false;
    }
}
