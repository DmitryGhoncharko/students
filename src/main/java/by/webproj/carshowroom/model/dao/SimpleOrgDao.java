package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Org;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class SimpleOrgDao implements OrgDao{
    private final ConnectionPool connectionPool;

    @Override
    public boolean add(String name, String desc, Date date, Long userId) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("insert into org(name,description,date_start,user_id) values (?,?,?,?)")){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,desc);
            preparedStatement.setDate(3,date);
            preparedStatement.setLong(4,userId);
            return preparedStatement.execute();
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
    }

    @Override
    public boolean remove(Long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from org where id = ?")){
            preparedStatement.setLong(1,id);
            return preparedStatement.execute();
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }

    }

    @Override
    public List<Org> getAllByUserId(Long userId) throws DaoException {
        List<Org> orgs = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select id, name, description, date_start, user_id from org where user_id = ?")){
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Org org = Org.builder().
                        id(resultSet.getLong(1)).
                        name(resultSet.getString(2)).
                        description(resultSet.getString(3)).
                        date(resultSet.getDate(4)).
                        userId(resultSet.getLong(5)).
                        build();
                orgs.add(org);
            }
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
        return orgs;
    }

    @Override
    public Optional<Org> getById(Long id) throws DaoException {
      try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select  id, name, description, date_start, user_id from  org where id = ?")){
          preparedStatement.setLong(1,id);
          ResultSet resultSet = preparedStatement.executeQuery();
          if(resultSet.next()){
              return  Optional.of(Org.builder().
                      id(resultSet.getLong(1)).
                      name(resultSet.getString(2)).
                      description(resultSet.getString(3)).
                      date(resultSet.getDate(4)).
                      userId(resultSet.getLong(5)).
                      build());
          }
      }catch (SQLException e){
          log.error("err",e);
          throw new DaoException("err",e);
      }
      return Optional.empty();
    }

    @Override
    public boolean update(Long id, String name, String desc, Date date) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update  org set name = ?, description = ?, date_start=? where id = ?")){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,desc);
            preparedStatement.setDate(3,date);
            preparedStatement.setLong(4,id);
            return preparedStatement.execute();
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
    }
}
