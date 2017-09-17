package com.kukuruznyak.bettingcompany.dao.impl.postgresql;

import com.kukuruznyak.bettingcompany.dao.interfaces.GenericDao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Set;

public abstract class PostgreSqlGenericDao<T, K extends Serializable> implements GenericDao<T, K> {
    public abstract T create();

    public abstract T read(K id);

    public abstract void update(T object);

    public abstract void delete(K id);

    public abstract Set<T> getAll() throws SQLException;
}
