package by.webproj.carshowroom.model.dao;


import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SimpleUserDao implements UserDao{
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserDao.class);
    private static final String SQL_ADD_USER = "insert into user(user_login, user_password, user_role_id) values (?,?,?)";
    private final ConnectionPool connectionPool;

    public SimpleUserDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public User addUser(User user) throws DaoException {
        try(final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS)){
           preparedStatement.setString(1,user.getUserLogin());
           preparedStatement.setString(2,user.getUserPassword());
           preparedStatement.setLong(3,user.getUserRole().ordinal());
           final int countCreatedRows = preparedStatement.executeUpdate();
           final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
           if(countCreatedRows>0 && generatedKeys.next()){
               return new User.Builder().
                       withUserId(generatedKeys.getLong(1)).
                       withUserLogin(user.getUserLogin()).
                       withUserPassword(user.getUserPassword()).
                       withUserRole(user.getUserRole()).
                       build();
           }
        }catch (SQLException sqlException){
            LOG.error("Cannot add user userLogin: " + user.getUserLogin() + " userPassword: " + user.getUserPassword() + " userRole: " + user.getUserRole(), sqlException );
            throw new DaoException("Cannot add user userLogin: " + user.getUserLogin() + " userPassword: " + user.getUserPassword() + " userRole: " + user.getUserRole());
        }
        LOG.error("Cannot add user userLogin: " + user.getUserLogin() + " userPassword: " + user.getUserPassword() + " userRole: " + user.getUserRole());
        throw new DaoException("Cannot add user userLogin: " + user.getUserLogin() + " userPassword: " + user.getUserPassword() + " userRole: " + user.getUserRole());
    }
}
