package com.kukuruznyak.bettingcompany.dao.impl;

import com.kukuruznyak.bettingcompany.dao.AbstractDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;
import com.kukuruznyak.bettingcompany.util.StringMessages;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public abstract class AbstractDaoImpl<T extends Model> implements AbstractDao<T> {
    protected DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();
    protected static final Logger LOGGER = Logger.getLogger(AbstractDaoImpl.class);
    protected static final ResourceBundle QUERIES = ResourceBundle.getBundle("queries");

    protected static final String SELECT_ALL_QUERY = "selectAll";
    protected static final String SELECT_BY_ID_QUERY = "selectById";
    protected static final String INSERT_QUERY = "insert";
    protected static final String UPDATE_QUERY = "update";
    protected static final String DELETE_QUERY = "delete";
    protected static final String ID_INDEX_IN_UPDATE = "idIndexInUpdate";
    protected static final String DELIMITER = ".";
    protected String currentModel;

    public AbstractDaoImpl() {
    }

    public AbstractDaoImpl(String currentModel) {
        this.currentModel = currentModel;
    }

    public T getById(Long id) throws PersistenceException {
        T model = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + DELIMITER + SELECT_BY_ID_QUERY));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                model = fillModel(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_SELECTING_ERROR) + currentModel +
                    StringMessages.getMessage(StringMessages.MESSAGE) + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        if (model == null) {
            LOGGER.info(currentModel + StringMessages.getMessage(StringMessages.NOT_FOUND));
        }
        return model;
    }

    public Collection<T> getAll() throws PersistenceException {
        List<T> modelList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERIES.getString(currentModel + DELIMITER + SELECT_ALL_QUERY));
            while (resultSet.next()) {
                modelList.add(fillModel(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_SELECTING_ERROR) + currentModel +
                    StringMessages.getMessage(StringMessages.MESSAGE) + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        return modelList;
    }

    protected Collection<T> getAllByConstrain(String query, String constrain) throws PersistenceException {
        Set<T> modelList = new HashSet<>();
        T model = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, constrain);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                model = fillModel(resultSet);
                modelList.add(model);
            }
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_SELECTING_ERROR) + currentModel +
                    StringMessages.getMessage(StringMessages.MESSAGE) + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        if (model == null) {
            LOGGER.info(currentModel + StringMessages.getMessage(StringMessages.NOT_FOUND));
        }
        return modelList;
    }

    public T add(T model) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + DELIMITER + INSERT_QUERY),
                    Statement.RETURN_GENERATED_KEYS);
            fillPreparedStatement(preparedStatement, model);
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                model.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_INSERTING_ERROR) + currentModel +
                    StringMessages.getMessage(StringMessages.MESSAGE) + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        return model;
    }

    public void update(T model) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel +
                    DELIMITER + UPDATE_QUERY));
            fillPreparedStatement(preparedStatement, model);
            preparedStatement.setLong(Integer.valueOf(QUERIES.getString(currentModel +
                    DELIMITER + ID_INDEX_IN_UPDATE)), model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_UPDATING_ERROR) + currentModel +
                    StringMessages.getMessage(StringMessages.MESSAGE) + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
    }

    public void delete(Long id) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel +
                    DELIMITER + DELETE_QUERY));
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_REMOVING_ERROR) + currentModel +
                    StringMessages.getMessage(StringMessages.MESSAGE) + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
    }

    protected abstract T fillModel(ResultSet resultSet) throws PersistenceException;

    protected abstract void fillPreparedStatement(PreparedStatement preparedStatement, T model) throws PersistenceException;
}
