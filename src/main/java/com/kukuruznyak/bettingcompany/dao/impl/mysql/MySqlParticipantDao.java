package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.entity.event.Participant;

import javax.sql.DataSource;
import java.util.List;

public class MySqlParticipantDao implements ParticipantDao {
    private static MySqlParticipantDao instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();

    public static MySqlParticipantDao getInstance() {
        if (instance == null) {
            instance = new MySqlParticipantDao();
        }
        return instance;
    }

    private MySqlParticipantDao() {
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
