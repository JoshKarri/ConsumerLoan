package com.demo.ConsumerLoan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.ConsumerLoan.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
