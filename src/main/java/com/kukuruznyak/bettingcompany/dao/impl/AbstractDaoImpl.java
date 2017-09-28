package com.kukuruznyak.bettingcompany.dao.impl;

import com.kukuruznyak.bettingcompany.dao.AbstractDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.entity.Model;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class AbstractDaoImpl<T extends Model> implements AbstractDao<T> {
    protected DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();
    protected static final Logger LOGGER = Logger.getLogger(AbstractDaoImpl.class);

    protected static final ResourceBundle QUERIES = ResourceBundle.getBundle("queries");
    protected static final String SELECT_ALL = "selectAll";
    protected static final String SELECT_BY_ID = "selectById";
    protected static final String INSERT = "insert";
    protected static final String UPDATE = "update";
    protected static final String DELETE = "delete";
    protected static final String ID_INDEX_IN_UPDATE = "idIndexInUpdate";
    protected String currentModel;

    public AbstractDaoImpl() {
    }

    public AbstractDaoImpl(String currentModel) {
        this.currentModel = currentModel;
    }

    public T getById(Long id) throws SQLException {
        T model = null;
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + "." + SELECT_BY_ID))) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                model = fillModel(resultSet);
                LOGGER.info(currentModel + " with id = " + id + " is found");
            }
        } catch (SQLException e) {
            LOGGER.error("Database error during selecting " + currentModel +
                    " with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
        if (model == null) {
            LOGGER.info(currentModel + " with id = " + id + " is not found");
        }
        return model;
    }

    public List<T> getAll() throws SQLException {
        List<T> modelList = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERIES.getString(currentModel + "." + SELECT_ALL));
            while (resultSet.next()) {
                modelList.add(fillModel(resultSet));
            }
            LOGGER.info(modelList.size() + " row(s) are found");
        } catch (SQLException e) {
            LOGGER.error("Database error during selecting " + currentModel +
                    " with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
        return modelList;
    }

    public T add(T model) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + "." + INSERT),
                Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(preparedStatement, model);
            int rowInserted = preparedStatement.executeUpdate();
            LOGGER.info(rowInserted + " row(s) inserted");

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                model.setId(generatedKeys.getLong(1));
                LOGGER.info(currentModel + " was inserted. Details: " + model.toString());
            }
        } catch (SQLException e) {
            LOGGER.error("Database error during inserting " + currentModel +
                    " with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
        return model;
    }

    public void update(T model) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + "." + UPDATE))) {
            fillPreparedStatement(preparedStatement, model);
            preparedStatement.setLong(Integer.valueOf(QUERIES.getString(currentModel + "." + ID_INDEX_IN_UPDATE)), model.getId());
            int rowUpdated = preparedStatement.executeUpdate();
            LOGGER.info(rowUpdated + " row(s) updated");
            LOGGER.info(currentModel + " with id " + model.getId() + " was updated in database. Details: " + model.toString());
        } catch (SQLException e) {
            LOGGER.error("Database error during updating " + currentModel +
                    " with message: " + e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
    }

    public void delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + "." + DELETE))) {
            preparedStatement.setLong(1, id);
            int rowDeleted = preparedStatement.executeUpdate();
            LOGGER.info(rowDeleted + " row(s) deleted");
            LOGGER.info(currentModel + " with id = " + id + " was successfully deleted from database.");
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Database error during removing " + currentModel + " with id = " + id +
                    " with message: " + e.getMessage());
        }
    }

    protected abstract T fillModel(ResultSet resultSet) throws SQLException;

    protected abstract void fillPreparedStatement(PreparedStatement preparedStatement, T model) throws SQLException;
}
