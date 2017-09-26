package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlUserDao implements UserDao {
    private static final String INSERT = "INSERT INTO user " +
            "(first_name, last_name, email, login, password, date_of_registration, user_role) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET " +
            "first_name = ?, last_name = ?, email = ?, login = ?, password = ?, date_of_registration = ?, user_role = ? " +
            "WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM user";
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SELECT_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String DELETE = "DELETE FROM user WHERE id = ?";
    private static MySqlUserDao instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();
    private static final Logger LOGGER = Logger.getLogger(MySqlUserDao.class);

    public static MySqlUserDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySqlUserDao();
            LOGGER.info("Instance of " + MySqlUserDao.class.getSimpleName() + " was created");
        }
        return instance;
    }

    private MySqlUserDao() throws SQLException {
    }

    @Override
    public User getById(Long id) throws SQLException {
        User user = null;
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = convertResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Database error during selecting with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
        if (user == null) {
            LOGGER.info("User with id = " + id + " is not found");
        } else {
            LOGGER.info("User with id = " + id + " is found");
        }
        return user;

    }

    @Override
    public User getByLogin(String login) throws SQLException {
        User user = null;
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = convertResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Database error during selecting with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
        if (user == null) {
            LOGGER.info("User with login = " + login + " is not found");
        } else {
            LOGGER.info("User with login = " + login + " is found");
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                userList.add(convertResultSetToUser(resultSet));
            }
            LOGGER.info(userList.size() + " row(s) are found");
        } catch (SQLException e) {
            LOGGER.error("Database error during selecting with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
        return userList;
    }

    @Override
    public User add(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setDate(6, new java.sql.Date(user.getDateOfRegistration().getTime()));
            preparedStatement.setString(7, user.getUserRole().toString());
            int rowInserted = preparedStatement.executeUpdate();
            LOGGER.info(rowInserted + " row(s) inserted");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                user.setId(generatedKeys.getLong(1));
                LOGGER.info("User " + user.toString() + " was inserted");
            }
        } catch (SQLException e) {
            LOGGER.error("Database error during inserting user: " + user.toString() +
                    " with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setDate(6, new java.sql.Date(user.getDateOfRegistration().getTime()));
            preparedStatement.setString(7, user.getUserRole().toString());
            preparedStatement.setLong(8, user.getId());
            int rowUpdated = preparedStatement.executeUpdate();
            LOGGER.info(rowUpdated + " row(s) updated");
            LOGGER.info("User with id " + user.getId() + " was updated in DB with following details: " + user.toString());
        } catch (SQLException e) {
            LOGGER.error("Database error during updating user: " + user.toString() +
                    " with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            int rowDeleted = preparedStatement.executeUpdate();
            LOGGER.info(rowDeleted + " row(s) deleted");
            LOGGER.info("User with id = " + id + " was successfully deleted from DB.");
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Database error during removing user with id = " + id +
                    " with message: " + e.getMessage());
        }
    }

    private User convertResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setDateOfRegistration(new Date(resultSet.getDate("date_of_registration").getTime()));
        user.setUserRole(UserRole.valueOf(resultSet.getString("user_role")));
        return user;
    }
}
