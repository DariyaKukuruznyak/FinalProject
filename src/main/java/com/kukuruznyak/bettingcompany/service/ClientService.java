package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class ClientService extends AbstractService {
    private static ClientService instance;

    public static ClientService getInstance() {
        if (instance == null) {
            synchronized (ClientService.class) {
                if (instance == null) {
                    instance = new ClientService();
                }
            }
        }
        return instance;
    }

    private ClientService() {
    }

    public synchronized Client add(Client client) {
        try {
            Connection connection = dataSource.getConnection();
            try {
                connection.setAutoCommit(false);
                UserDao userDao = daoFactory.getUserDao(connection);
                client.setId(userDao.add(client).getId());
                ClientDao clientDao = daoFactory.getClientDao(connection);
                clientDao.add(client);
                connection.commit();
                return client;
            } catch (SQLException e) {
                connection.rollback();
                throw new ServiceException(e);
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void update(Client client) {
        try {
            Connection connection = dataSource.getConnection();
            try {
                connection.setAutoCommit(false);
                UserDao userDao = daoFactory.getUserDao(connection);
                userDao.update(client);
                ClientDao clientDao = daoFactory.getClientDao(connection);
                clientDao.update(client);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new ServiceException(e);
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(Long id) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                UserDao userDao = daoFactory.getUserDao(connection);
                userDao.delete(id);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Client getClientById(Long id) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                return daoFactory.getClientDao(connection).getById(id);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Collection<Client> getAllClients() {
        try {
            try (Connection connection = dataSource.getConnection()) {
                return daoFactory.getClientDao(connection).getAll();
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}
