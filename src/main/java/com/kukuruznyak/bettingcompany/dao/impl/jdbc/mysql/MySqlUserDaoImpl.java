package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MySqlUserDaoImpl extends AbstractDaoImpl<User> implements UserDao {
    private static MySqlUserDaoImpl instance;

    private static final ResourceBundle QUERIES = ResourceBundle.getBundle("queries");
    private static final String SELECT_BY_LOGIN = "selectByLogin";
    private static final String SELECT_ALL_BY_ROLE = "selectAllByRole";
    private static final String SELECT_ROLE_BY_LOGIN = "selectRoleByLogin";

    public static MySqlUserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlUserDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlUserDaoImpl();
                    LOGGER.info("Instance of " + MySqlUserDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlUserDaoImpl() {
        super(User.class.getSimpleName());
    }

    @Override
    protected User fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new UserBuilder()
                    .buildId(resultSet.getLong("id"))
                    .buildFirstName(resultSet.getString("first_name"))
                    .buildLastName(resultSet.getString("last_name"))
                    .buildEmail(resultSet.getString("email"))
                    .buildLogin(resultSet.getString("login"))
                    .buildPassword(resultSet.getString("password"))
                    .buildDateOfRegistration(new Date(resultSet.getDate("date_of_registration").getTime()))
                    .buildUserRole(UserRole.valueOf(resultSet.getString("user_role")))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, User user) throws PersistenceException {
        try {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setDate(6, new java.sql.Date(user.getDateOfRegistration().getTime()));
            preparedStatement.setString(7, user.getUserRole().toString());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<User> getUsersByRole(String role) throws PersistenceException {
        return getAllByConstrain(QUERIES.getString(currentModel + "." + SELECT_ALL_BY_ROLE), role);
    }

    public User getByLogin(String login) throws PersistenceException {
        User user = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(QUERIES.getString(currentModel + "." + SELECT_BY_LOGIN));
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = fillModel(resultSet);
                LOGGER.info(currentModel + " with login = " + login + " is found");
            }
        } catch (SQLException e) {
            LOGGER.error("Database error during selecting with message: " + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        if (user == null) {
            LOGGER.info(currentModel + " with login = " + login + " is not found");
        }
        return user;
    }

    @Override
    public UserRole getUSerRoleByLogin(String login) {
        UserRole userRole = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(QUERIES.getString(currentModel + "." + SELECT_ROLE_BY_LOGIN));
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userRole = UserRole.valueOf(resultSet.getString("user_role"));
                LOGGER.info(currentModel + " with login = " + login + " has role " + userRole);
            }
        } catch (SQLException e) {
            LOGGER.error("Database error during selecting with message: " + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        return userRole;
    }
}
