package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class UserService extends AbstractService {
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    private UserService() {
    }

    public boolean isUserExist(String login, String password) throws ServiceException {
        User user;
        user = getUserByLogin(login);
        return user != null && user.getPassword().equals(password);
    }

    public User getUserByLogin(String login) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                UserDao userDao = daoFactory.getUserDao(connection);
                return userDao.getByLogin(login);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Collection<User> getStaff() {
        Collection<User> staff = getAllAdministrators();
        staff.addAll(getAllBookmakers());
        staff.addAll(getAllRiskControllers());
        return staff;
    }

    public boolean isValidUser(User user) {
        if (!user.getFirstName().matches("([A-Za-zА-Яа-я'ЇїІі ]{1,20})")) {
            user.setFirstName("");
            return false;
        }
        if (!user.getLastName().matches("([A-Za-zА-Яа-я'ЇїІі ]{1,20})")) {
            user.setLastName("");
            return false;
        }
        if (!user.getLogin().matches(".{4,20}")) {
            user.setLogin("");
            return false;
        }
        if (!user.getEmail().matches(".+@.+")) {
            user.setEmail("");
            return false;
        }
        if (!user.getPassword().matches(".{4,20}")) {
            user.setPassword("");
            return false;
        }
        return true;
    }

    public User add(User user) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                UserDao userDao = daoFactory.getUserDao(connection);
                return userDao.add(user);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public User getUserById(String id) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                UserDao userDao = daoFactory.getUserDao(connection);
                return userDao.getById(Long.valueOf(id));
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void update(User user) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                UserDao userDao = daoFactory.getUserDao(connection);
                userDao.update(user);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(String id) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                UserDao userDao = daoFactory.getUserDao(connection);
                userDao.delete(Long.valueOf(id));
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    private Collection<User> getAllAdministrators() {
        try {
            try (Connection connection = dataSource.getConnection()) {
                UserDao userDao = daoFactory.getUserDao(connection);
                return userDao.getUsersByRole(UserRole.ADMINISTRATOR.toString());
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    private Collection<User> getAllBookmakers() {
        try {
            try (Connection connection = dataSource.getConnection()) {
                UserDao userDao = daoFactory.getUserDao(connection);
                return userDao.getUsersByRole(UserRole.BOOKMAKER.toString());
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    private Collection<User> getAllRiskControllers() {
        try {
            try (Connection connection = dataSource.getConnection()) {
                UserDao userDao = daoFactory.getUserDao(connection);
                return userDao.getUsersByRole(UserRole.RISK_CONTROLLER.toString());
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}