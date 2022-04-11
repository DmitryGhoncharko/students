package by.webproj.carshowroom.validator;

import by.webproj.carshowroom.entity.Role;
import by.webproj.carshowroom.entity.User;

public class SimpleUserValidator implements UserValidator{
    @Override
    public boolean validateUserData(User user) {
        if(user.getUserLogin()!=null && user.getUserPassword()!=null && user.getUserRole()!=null){
            return user.getUserLogin().length()>0 && user.getUserLogin().length()<=100 && user.getUserPassword().length()>0 && user.getGetUserPassword().length()<=100;
        }
        return false;
    }
}
