package com.itsluisjim.cruddemo.rest;

import com.itsluisjim.cruddemo.dao.EmployeeDAOImpl;
import com.itsluisjim.cruddemo.entity.Employee;
import com.itsluisjim.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService =  employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.queryAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findByID(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee ID not found - "  + employeeId);
        }

        return employee;
    }

    // add mapping for POST /employees - add a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        // set id in JSON as 0 to force a save of an item and not an update
        employee.setId(0);

        Employee dbEmployee = employeeService.save(employee);

        return dbEmployee;
    }

    // add mapping to update an employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee dbEmployee = employeeService.save(employee);

        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findByID(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee ID not found " + employeeId);
        }

        employeeService.delete(employeeId);

        return "Deleted employee ID - " + employeeId;
    }
}
