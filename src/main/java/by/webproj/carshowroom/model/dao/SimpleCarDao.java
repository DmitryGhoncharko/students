package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SimpleCarDao implements CarDao {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleCarDao.class);
    private static final String SQL_ADD_CAR = "insert into car(car_name, car_description, car_image) values (?,?,?)";
    private static final String SQL_DELETE_CAR = "delete from car where car_id = ?";
    private static final String SQL_UPDATE_CAR = "update car set car_name = ?, car_description = ?, car_image = ? where car_id = ?";
    private static final String SQL_GET_ALL_CARS = "select car_id, car_name, car_description, car_image from  car";
    private final ConnectionPool connectionPool;

    public SimpleCarDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Car addCar(Car car) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_CAR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getCarName());
            preparedStatement.setString(2, car.getCarDescription());
            Blob blob = connection.createBlob();
            blob.setBytes(1,car.getCarImage().getBytes(StandardCharsets.UTF_8));
            preparedStatement.setBlob(3, blob);
            final int countRowsCreated = preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (countRowsCreated > 0 && generatedKeys.next()) {
                final Car createdCar = new Car.Builder().
                        withCarId(generatedKeys.getLong(1)).
                        withCarName(car.getCarName()).
                        withCarDescription(car.getCarDescription()).
                        withCarImage(car.getCarImage()).
                        build();
                return car;
            }
        } catch (SQLException sqlException) {
            LOG.error("Cannot add new car, carName: " + car.getCarName() + " carDescription: " + car.getCarDescription(), sqlException);
            throw new DaoException("Cannot add new car, carName: " + car.getCarName() + " carDescription: " + car.getCarDescription(), sqlException);
        }
        LOG.error("Cannot add new car, carName: " + car.getCarName() + " carDescription: " + car.getCarDescription());
        throw new DaoException("Cannot add new car, carName: " + car.getCarName() + " carDescription: " + car.getCarDescription());
    }

    @Override
    public boolean deleteCar(long carId) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CAR)) {
            preparedStatement.setLong(1, carId);
            final int countRowsDeleted = preparedStatement.executeUpdate();
            return countRowsDeleted > 0;
        } catch (SQLException sqlException) {
            LOG.error("Cannot delete car by id carId: " + carId, sqlException);
            throw new DaoException("Cannot delete car by id carId: " + carId, sqlException);
        }
    }

    @Override
    public Car updateCar(Car car) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CAR)) {
            preparedStatement.setString(1, car.getCarName());
            preparedStatement.setString(2, car.getCarDescription());
            byte[] bytes = car.getCarImage().getBytes(StandardCharsets.UTF_8);
            Blob blob = connection.createBlob();
            blob.setBytes(1,bytes);
            preparedStatement.setBlob(3,blob);
            preparedStatement.setLong(4, car.getCarId());
            final int countUpdatedRows = preparedStatement.executeUpdate();
            if (countUpdatedRows > 0) {
                return car;
            }
        } catch (SQLException sqlException) {
            LOG.error("Cannot update car by id, carId: " + car.getCarId() + " carName: " + car.getCarName() + " carDescription: " + car.getCarDescription(), sqlException);
            throw new DaoException("Cannot update car by id, carId: " + car.getCarId() + " carName: " + car.getCarName() + " carDescription: " + car.getCarDescription(), sqlException);
        }
        LOG.error("Cannot update car by id, carId: " + car.getCarId() + " carName: " + car.getCarName() + " carDescription: " + car.getCarDescription());
        throw new DaoException("Cannot update car by id, carId: " + car.getCarId() + " carName: " + car.getCarName() + " carDescription: " + car.getCarDescription());
    }

    @Override
    public List<Car> getCars() throws DaoException {
        final List<Car> carList = new ArrayList<>();
        try (final Connection connection = connectionPool.getConnection(); final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_CARS);
            while (resultSet.next()) {
                final Car car = new Car.Builder().
                        withCarId(resultSet.getLong(1)).
                        withCarName(resultSet.getString(2)).
                        withCarDescription(resultSet.getString(3)).
                        withCarImage(convertBlobToString(resultSet.getBlob(4))).
                        build();
                carList.add(car);
            }
        } catch (SQLException sqlException) {
            LOG.error("Cannot get all cars", sqlException);
            throw new DaoException("Cannot get all cars", sqlException);
        }
        return carList;
    }
    private String convertBlobToString(Blob blob) {

        try(InputStream inputStream = blob.getBinaryStream(); ByteArrayOutputStream outputStream =new ByteArrayOutputStream();){

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();

            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return base64Image;
        }catch (Exception e){

        }
        return "";

    }
}
