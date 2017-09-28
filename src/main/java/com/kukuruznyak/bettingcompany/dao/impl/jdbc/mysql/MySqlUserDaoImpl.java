package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class MySqlUserDaoImpl extends AbstractDaoImpl<User> implements UserDao {
    private static MySqlUserDaoImpl instance;

    private static final ResourceBundle QUERIES = ResourceBundle.getBundle("queries");
    private static final String SELECT_BY_LOGIN = "selectByLogin";

    public static MySqlUserDaoImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySqlUserDaoImpl();
            LOGGER.info("Instance of " + MySqlUserDaoImpl.class.getSimpleName() + " was created");
        }
        return instance;
    }

    private MySqlUserDaoImpl() throws SQLException {
        super(User.class.getSimpleName());
    }

    @Override
    protected User fillModel(ResultSet resultSet) throws SQLException {
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

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getLogin());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setDate(6, new java.sql.Date(user.getDateOfRegistration().getTime()));
        preparedStatement.setString(7, user.getUserRole().toString());
    }

    public User getByLogin(String login) throws SQLException {
        User user = null;
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.
                prepareStatement(QUERIES.getString(currentModel + "." + SELECT_BY_LOGIN))) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = fillModel(resultSet);
                LOGGER.info(currentModel + " with login = " + login + " is found");
            }
        } catch (SQLException e) {
            LOGGER.error("Database error during selecting with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
        if (user == null) {
            LOGGER.info(currentModel + " with login = " + login + " is not found");
        }
        return user;
    }
}
