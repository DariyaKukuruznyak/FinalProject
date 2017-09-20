package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.List;

public interface AbstractDao<T extends Model> {
    T getById(Long id);

    List<T> getAll();

    void add(T model);

    void update(T model);

    void delete(T model);
}
