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
import com.kukuruznyak.bettingcompany.util.StringMessages;

import java.sql.*;
import java.util.Collection;

public class MySqlBetDaoImpl extends AbstractDaoImpl<Bet> implements BetDao {
    private static MySqlBetDaoImpl instance;
    private static ClientDao clientDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getClientDao();
    private static BetItemDao betItemDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getBetItemDao();

    private static final String ID_COLUMN = "id";
    private static final String SUM_IN_COLUMN = "sum_in";
    private static final String SUM_OUT_COLUMN = "sum_out";
    private static final String TYPE_COLUMN = "type";
    private static final String RESULT_COLUMN = "result";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String DATE_COLUMN = "date";
    private static final String CLIENT_ID_COLUMN = "client_id";
    private static final String TOTAL_COEFFICIENT_COLUMN = "total_coefficient";

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
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + DELIMITER + INSERT_QUERY),
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
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_INSERTING_ERROR) + currentModel +
                    StringMessages.getMessage(StringMessages.MESSAGE) + e.getMessage());
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
                    .buildId(resultSet.getLong(ID_COLUMN))
                    .buildSumIn(resultSet.getBigDecimal(SUM_IN_COLUMN))
                    .buildSumOut(resultSet.getBigDecimal(SUM_OUT_COLUMN))
                    .buildType(TypeOfBet.valueOf(resultSet.getString(TYPE_COLUMN)))
                    .buildResult(ResultOfBet.valueOf(resultSet.getString(RESULT_COLUMN)))
                    .buildDescription(resultSet.getString(DESCRIPTION_COLUMN))
                    .buildDate(resultSet.getDate(DATE_COLUMN))
                    .buildClient(clientDao.getById(resultSet.getLong(CLIENT_ID_COLUMN)))
                    .buildTotalCoefficient(resultSet.getDouble(TOTAL_COEFFICIENT_COLUMN))
                    .buildItems(betItemDao.getAllByBetId(resultSet.getLong(ID_COLUMN)))
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