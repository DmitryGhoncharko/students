package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Image;
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
public class SimpleImageDao implements ImageDao {
    private static final String SQL_ADD_IMAGE = "insert into images(user_id) values(?)";

    private static final String SQL_DELETE_IMAGE_BY_ID = "delete from images where images_id = ?";

    private static final String SQL_SELECT_IMAGES_BY_USER_ID = "select images_id, u.user_id, user_login, user_password, user_role_id from images" + " left join user u on u.user_id = images.user_id" + " where images.user_id = ?";
    private final ConnectionPool connectionPool;

    @Override
    public long add(long userId) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_IMAGE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            log.error("Cannot add image", e);
            throw new DaoException("Cannot add image", e);
        }
        throw new DaoException("Cannot add image");
    }

    @Override
    public List<Image> getByUserId(long userId) throws DaoException {
        List<Image> images = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_IMAGES_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Image image = new Image.Builder().withId(resultSet.getLong(1)).withUser(new User.Builder().withUserId(resultSet.getLong(2)).withUserLogin(resultSet.getString(3)).withUserPassword(resultSet.getString(4)).withUserRole(Role.CLIENT).build()).build();
                images.add(image);
            }
        } catch (SQLException e) {
            log.error("Cannot find images by userid", e);
            throw new DaoException("Cannot find images by userid", e);
        }
        return images;
    }

    @Override
    public boolean deleteByImageId(long imageId) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_IMAGE_BY_ID)) {
            preparedStatement.setLong(1, imageId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Cannot delete image by id", e);
            throw new DaoException("Cannot delete image by id", e);
        }
    }
}
