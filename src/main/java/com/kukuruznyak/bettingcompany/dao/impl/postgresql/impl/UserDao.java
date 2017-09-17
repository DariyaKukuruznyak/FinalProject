package com.kukuruznyak.bettingcompany.dao.impl.postgresql.impl;

import com.kukuruznyak.bettingcompany.dao.impl.postgresql.PostgreSqlGenericDao;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.sql.SQLException;
import java.util.Set;

public class UserDao extends PostgreSqlGenericDao<User, String> {

    public User create() {
        return null;
    }

    public User read(String id) {
        return null;
    }

    public void update(User object) {

    }

    public void delete(String id) {

    }

    public Set<User> getAll() throws SQLException {
        return null;
    }
}
