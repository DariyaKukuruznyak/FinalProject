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
    T getById(Long id);

    Collection<T> getAll() ;

    T add(T model);

    void update(T model);

    void delete(Long id);

}
