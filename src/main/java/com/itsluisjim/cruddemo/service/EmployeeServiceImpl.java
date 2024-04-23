package com.itsluisjim.cruddemo.service;

import com.itsluisjim.cruddemo.dao.EmployeeDAO;
import com.itsluisjim.cruddemo.dao.EmployeeDAOImpl;
import com.itsluisjim.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<Employee> queryAllEmployees() {
        return employeeDAO.queryAllEmployees();
    }

    @Override
    public Employee findByID(int id) {
        return employeeDAO.findByID(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        employeeDAO.delete(id);
    }
}
