package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class UserService extends AbstractService {
    private static UserService instance;
    private UserDao userDao = daoFactory.getUserDao();

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                    LOGGER.info("Instance of " + UserService.class.getSimpleName() + " was created");
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
        return userDao.getByLogin(login);
    }

    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    public User add(User user) {
        return userDao.add(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public List<User> getAllAdministrators() {
        return userDao.getUsersByRole(UserRole.ADMINISTRATOR.toString());
    }

    public List<User> getAllBookmakers() {
        return userDao.getUsersByRole(UserRole.BOOKMAKER.toString());
    }

    public List<User> getAllRiskControllers() {
        return userDao.getUsersByRole(UserRole.RISK_CONTROLLER.toString());
    }

    public List<User> geStaff() {
        List<User> staff = getAllAdministrators();
        staff.addAll(getAllBookmakers());
        staff.addAll(getAllRiskControllers());
        return staff;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        return clients;
    }
}
