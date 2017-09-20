package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.entity.event.Participant;

import java.sql.Connection;
import java.util.List;

public class ParticipantDaoMySql implements ParticipantDao {
    private final Connection connection;

    public ParticipantDaoMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Participant getById(Long id) {
        return null;
    }

    @Override
    public List<Participant> getAll() {
        return null;
    }

    @Override
    public void add(Participant model) {

    }

    @Override
    public void update(Participant model) {

    }

    @Override
    public void delete(Participant model) {

    }
}
