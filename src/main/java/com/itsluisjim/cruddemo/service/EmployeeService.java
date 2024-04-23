package com.itsluisjim.cruddemo.service;

import com.itsluisjim.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> queryAllEmployees();

    Employee findByID(int id);

    Employee save(Employee employee);

    void delete(int id);
}
