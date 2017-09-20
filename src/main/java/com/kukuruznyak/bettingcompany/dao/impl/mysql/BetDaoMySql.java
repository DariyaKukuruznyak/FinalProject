package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.entity.Bet;

import java.sql.Connection;
import java.util.List;

public class BetDaoMySql implements BetDao{
    private final Connection connection;

    public BetDaoMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Bet getById(Long id) {
        return null;
    }

    @Override
    public List<Bet> getAll() {
        return null;
    }

    @Override
    public void add(Bet model) {

    }

    @Override
    public void update(Bet model) {

    }

    @Override
    public void delete(Bet model) {

    }
}
