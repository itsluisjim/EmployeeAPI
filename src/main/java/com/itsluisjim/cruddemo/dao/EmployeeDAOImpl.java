package com.itsluisjim.cruddemo.dao;

import com.itsluisjim.cruddemo.entity.Employee;
import com.itsluisjim.cruddemo.kafka.KafkaSender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;
    private KafkaSender kafkaSender;

    // set up constructor injection
    @Autowired // optional when there's only one constructor
    public EmployeeDAOImpl(EntityManager entityManager, KafkaSender kafkaSender){
        this.entityManager = entityManager;
        this.kafkaSender = kafkaSender;
    }

    @Override
    public List<Employee> queryAllEmployees() {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);

        return query.getResultList();
    }

    @Override
    public Employee findByID(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);
        this.kafkaSender.sendMessage(employee, "user-creation-topic");

        return dbEmployee;
    }


    @Override
    public void delete(int id) {
        Employee employee =  entityManager.find(Employee.class, id);

        entityManager.remove(employee);
        this.kafkaSender.sendMessage(employee, "user-deletion-topic");

    }
}
