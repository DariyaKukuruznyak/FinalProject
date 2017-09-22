package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.EmployeeDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.entity.user.Employee;

import javax.sql.DataSource;
import java.util.List;

public class MySqlEmployeeDao implements EmployeeDao {
    private static MySqlEmployeeDao instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();

    public static MySqlEmployeeDao getInstance() {
        if (instance == null) {
            instance = new MySqlEmployeeDao();
        }
        return instance;
    }

    private MySqlEmployeeDao() {
    }
    @Override
    public Employee getById(Long id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public void add(Employee model) {

    }

    @Override
    public void update(Employee model) {

    }

    @Override
    public void delete(Employee model) {

    }
}
