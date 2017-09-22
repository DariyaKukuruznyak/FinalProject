package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDao<T extends Model> {
    T getById(Long id) throws SQLException;

    List<T> getAll() throws SQLException;

    void add(T model) throws SQLException;

    void update(T model) throws SQLException;

    void delete(T model) throws SQLException;
}
