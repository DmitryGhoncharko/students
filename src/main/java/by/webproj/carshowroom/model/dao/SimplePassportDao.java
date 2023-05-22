package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Passport;
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
public class SimplePassportDao implements PassportDao{
    private final ConnectionPool connectionPool;

    @Override
    public long add(Long id, String customer, String org, String ruk, String gen, String tex, String ots) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("insert into passport(customer,org,ruk,gen,tex,ots,user_id) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,customer);
            preparedStatement.setString(2,org);
            preparedStatement.setString(3,ruk);
            preparedStatement.setString(4,gen);
            preparedStatement.setString(5,tex);
            preparedStatement.setString(6,ots);
            preparedStatement.setLong(7,id);
            preparedStatement.execute();
            ResultSet resultSet=  preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getLong(1);
            }
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
        throw new DaoException("err");
    }

    @Override
    public boolean remove(Long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from passport where id = ?")){
                preparedStatement.setLong(1,id);
                return preparedStatement.execute();
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
    }

    @Override
    public List<Passport> getAllByUserId(Long userId) throws DaoException {
        List<Passport> passports = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();PreparedStatement preparedStatement = connection.prepareStatement("select id, customer,org,ruk,gen,tex,ots,user_id from passport where user_id = ?")){
          preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
          while (resultSet.next()){
              Passport passport = Passport.builder().
                      id(resultSet.getLong(1)).
                      customer(resultSet.getString(2)).
                      org(resultSet.getString(3)).
                      ruk(resultSet.getString(4)).
                      gen(resultSet.getString(5)).
                      tex(resultSet.getString(6)).
                      ots(resultSet.getString(7)).
                      userId(resultSet.getLong(8)).
                      build();
              passports.add(passport);
          }
        }catch (SQLException e){
            log.error("errr",e);
            throw new DaoException("errr",e);
        }
        return passports;
    }

    @Override
    public Optional<Passport> getById(Long id) throws DaoException {
       try(Connection connection = connectionPool.getConnection();PreparedStatement preparedStatement = connection.prepareStatement("select id, customer,org,ruk,gen,tex,ots,user_id from passport where id = ?")){
           preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
           if (resultSet.next()){
               Passport passport = Passport.builder().
                       id(resultSet.getLong(1)).
                       customer(resultSet.getString(2)).
                       org(resultSet.getString(3)).
                       ruk(resultSet.getString(4)).
                       gen(resultSet.getString(5)).
                       tex(resultSet.getString(6)).
                       ots(resultSet.getString(7)).
                       userId(resultSet.getLong(8)).
                       build();
              return Optional.of(passport);
           }
       }catch (SQLException e){
           log.error("err",e);
           throw new DaoException("err",e);
       }
       return Optional.empty();
    }

    @Override
    public boolean update(Long id, String customer, String org, String ruk, String gen, String tex, String ots) throws DaoException {
        try(Connection connection = connectionPool.getConnection();PreparedStatement preparedStatement = connection.prepareStatement("update passport set customer=?, org = ?, ruk=?, gen=?,tex=?,ots=? where id = ?")){
            preparedStatement.setString(1,customer);
            preparedStatement.setString(2,org);
            preparedStatement.setString(3,ruk);
            preparedStatement.setString(4,gen);
            preparedStatement.setString(5,tex);
            preparedStatement.setString(6,ots);
            preparedStatement.setLong(7,id);
            return preparedStatement.execute();
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
    }
}
