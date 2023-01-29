package com.eldorado.employeeservice.repository;

import com.eldorado.employeeservice.domain.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    public Employee findByDocument(String documentNumber);
    public void deleteByDocument(String documentNumber);

}
