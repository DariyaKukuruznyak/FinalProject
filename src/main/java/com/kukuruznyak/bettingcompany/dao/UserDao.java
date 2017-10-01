package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    List<User> getUsersByRole(String role);

    User getByLogin(String login);
}
