package com.demo.ConsumerLoan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.ConsumerLoan.entity.Employee;
import com.demo.ConsumerLoan.repository.EmployeeRepository;

@SpringBootApplication
public class ConsumerLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerLoanApplication.class, args);
	}
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Bean
	public void loadEmployees() {
		empRepository.save(new Employee("1", "Employee1"));
		empRepository.save(new Employee("2", "Employee2"));
		empRepository.save(new Employee("3", "Employee3"));
		empRepository.save(new Employee("4", "Employee4"));
	}
	
	// Collaterals are in Constants.

}
