package com.example.constructions.Repositories;

import com.example.constructions.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findBySurnameContains(String surname);
}
