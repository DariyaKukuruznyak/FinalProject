package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.sql.Connection;
import java.util.List;

public class ClientDaoMySql implements ClientDao {
    private final Connection connection;

    public ClientDaoMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client getById(Long id) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public void add(Client model) {

    }

    @Override
    public void update(Client model) {

    }

    @Override
    public void delete(Client model) {

    }
}
