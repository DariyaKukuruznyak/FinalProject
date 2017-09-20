package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.EmployeeDao;
import com.kukuruznyak.bettingcompany.entity.user.Employee;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.sql.Connection;
import java.util.List;

public class EmployeeDaoMySql implements EmployeeDao {
    private final Connection connection;

    public EmployeeDaoMySql(Connection connection) {
        this.connection = connection;
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
