package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.util.Collection;
import java.util.List;

public interface UserDao extends AbstractDao<User> {
    Collection<User> getUsersByRole(String role)throws PersistenceException;

    User getByLogin(String login)throws PersistenceException;

    UserRole getUserRoleByLogin(String login);
}
