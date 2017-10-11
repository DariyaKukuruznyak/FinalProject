package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface AbstractDao<T extends Model>{
    T getById(Long id) throws PersistenceException;

    Collection<T> getAll() throws PersistenceException;

    T add(T model) throws PersistenceException;

    void update(T model) throws PersistenceException;

    void delete(Long id) throws PersistenceException;

}
