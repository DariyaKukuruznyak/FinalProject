package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.user.User;

import java.sql.SQLException;

public interface UserDao extends AbstractDao<User> {

    User getByLogin(String login) throws SQLException;
}
