package com.kukuruznyak.bettingcompany.dao.interfaces;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Set;

public interface GenericDao<T, K extends Serializable> {
    T create();

    T read(K id);

    void update(T object);

    void delete(K id);

    Set<T> getAll() throws SQLException;
}
