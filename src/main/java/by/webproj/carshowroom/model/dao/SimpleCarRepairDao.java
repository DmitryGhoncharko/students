package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.CarRepair;
import by.webproj.carshowroom.entity.Role;
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
public class SimpleCarRepairDao implements CarRepairDao{
    private static final String SQL_ADD_CAR_REPAIR = "insert into car_repair(car_repair_name, car_repair_description, car_repair_cost, car_repair_user_id, car_repair_complete) values(?,?,?,?,?)";
    private static final String SQL_UPDATE_CAR_REPAIR = "update car_repair set car_repair_complete = true where car_repair_id = ?";
    private static final String SQL_SELECT_BY_USER_ID = "select car_repair_id, car_repair_name,car_repair_description,car_repair_cost, user_id, user_role_id, user_login, user_login car_repair_complete from car_repair\n" +
            "inner join user u on car_repair.car_repair_user_id = u.user_id" +
            " where car_repair_user_id = ?";
    private static final String SQL_SELECT_BY_USER_ID_WHERE_STATUS_COMPLETE = "select car_repair_id, car_repair_name,car_repair_description,car_repair_cost, user_id, user_role_id, user_login, user_login car_repair_complete from car_repair\n" +
            "inner join user u on car_repair.car_repair_user_id = u.user_id\n" +
            "where car_repair_complete = true and car_repair_user_id = ?";

    private static final String SQL_GET_ALL = "select car_repair_id, car_repair_name,car_repair_description,car_repair_cost, user_id, user_role_id, user_login, car_repair_complete from car_repair\n" +
            "inner join user u on car_repair.car_repair_user_id = u.user_id";
    private final ConnectionPool connectionPool;
    @Override
    public boolean add(CarRepair carRepair) throws DaoException {
        try(Connection connection=  connectionPool.getConnection(); PreparedStatement preparedStatement=  connection.prepareStatement(SQL_ADD_CAR_REPAIR)){
            preparedStatement.setString(1,carRepair.getName());
            preparedStatement.setString(2,carRepair.getDescription());
            preparedStatement.setDouble(3,carRepair.getCost());
            preparedStatement.setLong(4,carRepair.getUser().getId());
            preparedStatement.setBoolean(5,false);
            return preparedStatement.executeUpdate()>0;
        }catch (SQLException e){
            log.error("error",e);
            throw new DaoException("error",e);
        }
    }

    @Override
    public boolean update(long carRepairId) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CAR_REPAIR)){
            preparedStatement.setLong(1,carRepairId);
            return preparedStatement.executeUpdate()>0;
        }catch (SQLException e){
            log.error("error",e);
            throw new DaoException("error",e);
        }
    }

    @Override
    public List<CarRepair> selectByUserId(long userId) throws DaoException {
        List<CarRepair> carRepairs = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER_ID)){
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CarRepair carRepair = new CarRepair.Builder().
                withId(resultSet.getLong(1)).
                        withName(resultSet.getString(2)).
                        withDescription(resultSet.getString(3)).
                        withCost(resultSet.getDouble(4)).
                        withUser(new User.Builder().
                                withUserId(resultSet.getLong(5)).
                                withUserRole(Role.CLIENT).
                                withUserLogin(resultSet.getString(7)).
                                build()).
                        withComplete(resultSet.getBoolean(8)).
                        build();
                carRepairs.add(carRepair);
            }
        }catch (SQLException e){
            log.error("error",e);
            throw new DaoException("error",e);
        }
        return carRepairs;
    }

    @Override
    public List<CarRepair> selectByUserIdWhereStatusComplete(long userId) throws DaoException {
        List<CarRepair> carRepairs = new ArrayList<>();
       try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER_ID_WHERE_STATUS_COMPLETE)){
           preparedStatement.setLong(1,userId);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               CarRepair carRepair = new CarRepair.Builder().
                       withId(resultSet.getLong(1)).
                       withName(resultSet.getString(2)).
                       withDescription(resultSet.getString(3)).
                       withCost(resultSet.getDouble(4)).
                       withUser(new User.Builder().
                               withUserId(resultSet.getLong(5)).
                               withUserRole(Role.CLIENT).
                               withUserLogin(resultSet.getString(7)).
                               build()).
                       withComplete(resultSet.getBoolean(8)).
                       build();
               carRepairs.add(carRepair);
           }

       }catch (SQLException e){
           log.error("error",e);
           throw new DaoException("error",e);
       }
       return carRepairs;
    }

    @Override
    public List<CarRepair> getAll() throws DaoException {
        List<CarRepair> carRepairs = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement preparedStatement = connection.createStatement()){
            ResultSet resultSet = preparedStatement.executeQuery(SQL_GET_ALL);
            while (resultSet.next()){
                CarRepair carRepair = new CarRepair.Builder().
                        withId(resultSet.getLong(1)).
                        withName(resultSet.getString(2)).
                        withDescription(resultSet.getString(3)).
                        withCost(resultSet.getDouble(4)).
                        withUser(new User.Builder().
                                withUserId(resultSet.getLong(5)).
                                withUserRole(Role.values()[(int) resultSet.getLong(6)]).
                                withUserLogin(resultSet.getString(7)).
                                build()).
                        withComplete(resultSet.getBoolean(8)).
                        build();
                carRepairs.add(carRepair);
            }

        }catch (SQLException e){
            log.error("error",e);
            throw new DaoException("error",e);
        }
        return carRepairs;
    }
}
