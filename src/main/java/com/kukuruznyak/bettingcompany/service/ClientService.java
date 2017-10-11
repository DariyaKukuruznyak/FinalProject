package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.Collection;
import java.util.List;

public class ClientService extends AbstractService {
    private static ClientService instance;
    private ClientDao clientDao = daoFactory.getClientDao();

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

    public Collection<Client> getAllClients() {
        return clientDao.getAll();
    }

    public Client add(Client client) {
        return clientDao.add(client);
    }

    public Client getClientById(Long id) {
        return clientDao.getById(id);
    }

    public void update(Client client) {
        clientDao.update(client);
    }

    public void delete(String id) {
        clientDao.delete(Long.valueOf(id));
    }
}
