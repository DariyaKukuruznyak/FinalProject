package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.entity.user.Client;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class MySqlClientDao implements ClientDao {
    private static MySqlClientDao instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();
    private static final String INSERT = "INSERT INTO client (max_bet, balance, description) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE client SET max_bet = ?, balance = ?, description = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM client";
    private static final String SELECT_BY_ID = "SELECT * FROM client WHERE id = ?";
    private static final String DELETE = "DELETE FROM client WHERE id = ?";

    /*

        @Override
        public User getById(Long id) throws SQLException {
            User user = new User();
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user=  convertResultSetToUser(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) connection.close();
            }
            return user;

        }

        @Override
        public User findByLogin(String login) throws SQLException {
            User user = new User();
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_LOGIN)) {
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user=  convertResultSetToUser(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) connection.close();
            }
            return user;
        }

        @Override
        public List<User> getAll() throws SQLException {
            List<User> userList = new ArrayList<>();
            Connection connection = dataSource.getConnection();
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL);
                while (resultSet.next()) {
                    userList.add(convertResultSetToUser(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) connection.close();
            }
            return userList;
        }

        @Override
        public User add(User user) throws SQLException {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setDate(6, new java.sql.Date(user.getDateOfRegistration().getTime()));
                preparedStatement.setString(7, user.getUserRole().toString());
                user.setId((long) preparedStatement.executeUpdate());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) connection.close();
            }
            return user;
        }

        @Override
        public void update(User user) throws SQLException {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setDate(6, new java.sql.Date(user.getDateOfRegistration().getTime()));
                preparedStatement.setString(7, user.getUserRole().toString());
                preparedStatement.setLong(8, user.getId());
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " Rows affected.");
                System.out.println("User with id " + user.getId() + " was updated in DB with following details: " + user.toString());

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) connection.close();
            }
        }

        @Override
        public void delete(Long id) throws SQLException {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                System.out.println("User with id = " + id + " was successfully deleted from DB.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private User convertResultSetToUser(ResultSet resultSet) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setDateOfRegistration(new Date(resultSet.getDate("date_of_registration").getTime()));
            user.setUserRole(UserRole.valueOf(resultSet.getString("user_role")));
            return user;
        }*/
    public static MySqlClientDao getInstance() {
        if (instance == null) {
            instance = new MySqlClientDao();
        }
        return instance;
    }

    private MySqlClientDao() {
    }

    @Override
    public Client getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        return null;
    }

    @Override
    public Client add(Client client) throws SQLException {
        return null;
    }

    @Override
    public void update(Client model) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
