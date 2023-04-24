package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.CarRepair;
import by.webproj.carshowroom.entity.Request;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class SimpleRequestDao implements RequestDao{
    private static final String SQL_ADD_REQUEST = "insert into request(request_name,request_description,request_user_id) values(?,?,?)";

    private static final String SQL_REMOVE_BY_ID = "delete from request where request_id = ?";

    private static final String SQL_GET_ALL = "select request_id, request_name,request_description,request_user_id from request";
    private final ConnectionPool connectionPool;

    @Override
    public boolean add(Request request) throws DaoException {
        try(Connection connection= connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_REQUEST)){
            preparedStatement.setString(1,request.getName());
            preparedStatement.setString(2,request.getDescription());
            preparedStatement.setLong(3,request.getUser().getId());
            return preparedStatement.executeUpdate()>0;
        }catch (SQLException e){
            log.error("error",e);
            throw new DaoException("error",e);
        }
    }

    @Override
    public boolean removeByUserId(long id) throws DaoException {
        try(Connection connection= connectionPool.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_BY_ID)){
            preparedStatement.setLong(1,id);
            return preparedStatement.executeUpdate()>0;
        }catch (SQLException e){
            log.error("error",e);
            throw new DaoException("error",e);
        }
    }

    @Override
    public List<Request> getAll() throws DaoException {
        List<Request> requests = new ArrayList<>();
        try(Connection connection= connectionPool.getConnection(); Statement statement=  connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            while (resultSet.next()){
                Request request = new Request.Builder().
                        withId(resultSet.getLong(1)).
                        withName(resultSet.getString(2)).
                        withDescription(resultSet.getString(3)).
                        withUser(new User.Builder().withUserId(resultSet.getLong(4)).build()).build();
                requests.add(request);
            }
        }catch (SQLException e){
            log.error("error",e);
            throw new DaoException("error",e);
        }
        return requests;
    }
}
