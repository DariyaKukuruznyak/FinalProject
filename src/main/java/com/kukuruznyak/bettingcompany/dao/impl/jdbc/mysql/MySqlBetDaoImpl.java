package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.dao.BetItemDao;
import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.BetItem;
import com.kukuruznyak.bettingcompany.entity.bet.ResultOfBet;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.bet.builder.BetBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.*;
import java.util.Collection;

public class MySqlBetDaoImpl extends AbstractDaoImpl<Bet> implements BetDao {
    private static MySqlBetDaoImpl instance;
    private static ClientDao clientDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getClientDao();
    private static BetItemDao betItemDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getBetItemDao();

    public static MySqlBetDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlBetDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlBetDaoImpl();
                }
            }
        }
        return instance;
    }

    private MySqlBetDaoImpl() {
        super(Bet.class.getSimpleName());
    }

    @Override
    public Bet add(Bet bet) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + DELIMITER + INSERT),
                    Statement.RETURN_GENERATED_KEYS);
            fillPreparedStatement(preparedStatement, bet);
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                bet.setId(generatedKeys.getLong(1));
            }
            for (BetItem betItem : bet.getItems()) {
                betItem.setBetId(bet.getId());
                betItemDao.add(betItem);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(DB_INSERTING_ERROR + currentModel + MESSAGE + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        return bet;
    }

    @Override
    public void update(Bet bet) {
        super.update(bet);
        for (BetItem betItem : bet.getItems()) {
            betItemDao.update(betItem);
        }
    }

    @Override
    public Collection<Bet> getByUserId(Long clientId) {
        return null;
    }

    @Override
    protected Bet fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new BetBuilder()
                    .buildId(resultSet.getLong("id"))
                    .buildSumIn(resultSet.getBigDecimal("sum_in"))
                    .buildSumOut(resultSet.getBigDecimal("sum_out"))
                    .buildType(TypeOfBet.valueOf(resultSet.getString("type")))
                    .buildResult(ResultOfBet.valueOf(resultSet.getString("result")))
                    .buildDescription(resultSet.getString("description"))
                    .buildDate(resultSet.getDate("date"))
                    .buildClient(clientDao.getById(resultSet.getLong("client_id")))
                    .buildTotalCoefficient(resultSet.getDouble("total_coefficient"))
                    .buildItems(betItemDao.getAllByBetId(resultSet.getLong("id")))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Bet bet) throws PersistenceException {
        try {
            preparedStatement.setString(1, bet.getSumIn().toString());
            preparedStatement.setString(2, bet.getSumOut().toString());
            preparedStatement.setString(3, bet.getType().toString());
            preparedStatement.setString(4, bet.getResult().toString());
            preparedStatement.setString(5, bet.getDescription());
            preparedStatement.setDate(6, new java.sql.Date(bet.getDate().getTime()));
            preparedStatement.setLong(7, bet.getClient().getId());
            preparedStatement.setDouble(8, bet.getTotalCoefficient());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}