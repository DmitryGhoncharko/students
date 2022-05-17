package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.entity.Favorites;
import by.webproj.carshowroom.entity.Role;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleFavoritesDao implements FavoritesDao{
    private static final Logger LOG =  LoggerFactory.getLogger(SimpleFavoritesDao.class);
    private static final String SQL_FIND_ALL_FAVORITES_BY_USER_ID = "select  c.car_id, c.car_name, c.car_description from  favorite_car" +
            " left join car c on favorite_car.car_id = c.car_id" +
            " where user_id = ?";
    private static final String SQL_ADD_TO_FAVORITES = "insert into favorite_car(user_id, car_id)  values (?,?)";

    private static final String SQL_FIND_FAVORITES_BY_ID = "select favorite_car_id, u.user_id, u.user_login, u.user_password, u.user_role_id, c.car_id, c.car_name, c.car_description from favorite_car" +
            " left join car c on favorite_car.car_id = c.car_id" +
            " left join user u on favorite_car.user_id = u.user_id" +
            " left join role r on u.user_role_id = r.role_id" +
            " where favorite_car_id = ?";
    private static final String SQL_DELETE_FAVORITES_BY_ID = "delete from favorite_car where favorite_car_id = ?";
    private final ConnectionPool connectionPool;

    public SimpleFavoritesDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Car> findFavoritesCarByUserId(long userId) throws DaoException {
        final List<Car> cars = new ArrayList<>();
        try(final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_FAVORITES_BY_USER_ID)){
            preparedStatement.setLong(1,userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                final Car car = new Car.Builder().
                        withCarId(resultSet.getLong(1)).
                        withCarName(resultSet.getString(2)).
                        withCarDescription(resultSet.getString(3)).
                        build();
                cars.add(car);
            }
        }catch (SQLException e){
            LOG.error("Cannot find favorites by id, userId: " + userId, e);
            throw new DaoException("Cannot find favorites by id, userId: " + userId, e);
        }
        return cars;
    }

    @Override
    public int addToFavorites(long userId, long carId) throws DaoException {
        try(final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_TO_FAVORITES, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1,userId);
            preparedStatement.setLong(2,carId);
            final int countRowsUpdated = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return (int) resultSet.getLong(1);
            }
        }catch (SQLException e){
            LOG.error("Cannot add to favorites",e);
            throw new DaoException("Cannot add to favorites",e);
        }
        return -1;
    }

    @Override
    public Optional<Favorites> findFavoritesById(long id) throws DaoException {
        try(final  Connection connection=  connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_FAVORITES_BY_ID)){
            preparedStatement.setLong(1,id);
            final ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                final Favorites favorites = new Favorites.Builder().
                        withId(resultSet.getLong(1)).
                        withUser(new User.Builder().
                                withUserId(resultSet.getLong(2)).
                                withUserLogin(resultSet.getString(3)).
                                withUserPassword(resultSet.getString(4)).
                                withUserRole(Role.CLIENT).build()).
                        withCar(new Car.Builder().
                                withCarId(resultSet.getLong(5)).
                                withCarName(resultSet.getString(6)).
                                withCarDescription(resultSet.getString(7)).build()).build();
                return Optional.of(favorites);
            }
        }catch (SQLException e){
            LOG.error("Cannot find favories by id",e);
            throw new DaoException("Cannot find favories by id",e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        try(final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_FAVORITES_BY_ID)){
            preparedStatement.setLong(1,id);
            int countRowsDeleted = preparedStatement.executeUpdate();
            return countRowsDeleted>0;
        }catch (SQLException e){
            LOG.error("Cannot delete by id",e);
            throw new DaoException("Cannot delete by id",e);
        }
    }
}
