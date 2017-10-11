package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class MySqlUserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

    private static final String SELECT_BY_LOGIN_QUERY = "selectByLogin";
    private static final String SELECT_ALL_BY_ROLE_QUERY = "selectAllByRole";
    private static final String SELECT_ROLE_BY_LOGIN_QUERY = "selectRoleByLogin";

    private static final String ID_COLUMN = "id";
    private static final String FIRST_NAME_COLUMN = "first_name";
    private static final String LAST_NAME_COLUMN = "last_name";
    private static final String EMAIL_COLUMN = "email";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASSWORD_COLUMN = "password";
    private static final String REGISTRATION_DATE_COLUMN = "date_of_registration";
    private static final String USER_ROLE_COLUMN = "user_role";

    public MySqlUserDaoImpl(Connection connection) {
        super(connection, User.class.getSimpleName());
    }

    @Override
    protected User fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new UserBuilder()
                    .buildId(resultSet.getLong(ID_COLUMN))
                    .buildFirstName(resultSet.getString(FIRST_NAME_COLUMN))
                    .buildLastName(resultSet.getString(LAST_NAME_COLUMN))
                    .buildEmail(resultSet.getString(EMAIL_COLUMN))
                    .buildLogin(resultSet.getString(LOGIN_COLUMN))
                    .buildPassword(resultSet.getString(PASSWORD_COLUMN))
                    .buildDateOfRegistration(new Date(resultSet.getDate(REGISTRATION_DATE_COLUMN).getTime()))
                    .buildUserRole(UserRole.valueOf(resultSet.getString(USER_ROLE_COLUMN)))
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
    public Collection<User> getUsersByRole(String role) throws PersistenceException {
        return getAllByConstrain(QUERIES.getString(currentModel + DELIMITER + SELECT_ALL_BY_ROLE_QUERY), role);
    }

    public User getByLogin(String login) throws PersistenceException {
        User user = null;
            try (PreparedStatement preparedStatement = connection.
                    prepareStatement(QUERIES.getString(currentModel + DELIMITER + SELECT_BY_LOGIN_QUERY))){
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = fillModel(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_SELECTING_ERROR) + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        if (user == null) {
            LOGGER.info(currentModel + StringMessages.getMessage(StringMessages.NOT_FOUND));
        }
        return user;
    }

    @Override
    public UserRole getUserRoleByLogin(String login) {
        UserRole userRole = null;
            try ( PreparedStatement preparedStatement = connection.
                    prepareStatement(QUERIES.getString(currentModel + DELIMITER + SELECT_ROLE_BY_LOGIN_QUERY))){
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userRole = UserRole.valueOf(resultSet.getString(USER_ROLE_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_SELECTING_ERROR) + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        return userRole;
    }
}
