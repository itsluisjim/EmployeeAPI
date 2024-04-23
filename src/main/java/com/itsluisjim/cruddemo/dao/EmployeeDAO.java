package com.itsluisjim.cruddemo.dao;

import com.itsluisjim.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> queryAllEmployees();

    Employee findByID(int id);

    Employee save(Employee employee);

    void delete(int id);
}
