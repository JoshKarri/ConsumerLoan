package com.demo.ConsumerLoan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ConsumerLoan.request.LoanApplication;
import com.demo.ConsumerLoan.service.EmployeeService;
import com.demo.ConsumerLoan.service.LoanService;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private LoanService loanService;
	
	// ASSIGN A EMPLOYEE
	
	
	// EMPLOYEE PROCESSES/APPROVES THE LOAN
	@PostMapping("/approveLoan")
	public String approveLoan(@RequestBody LoanApplication application) throws Exception 
	{
		if(this.employeeService.isEmployeeExist(application.getEmployeeId())) {
			if(this.loanService.isLoanExistsForCustomer(application)) {
				return this.employeeService.approveLoan(application);
			}
			else {
				return "No Loans found with Loan ID";
			}
		}
		else {
			return "Employee ID mandatory";
		}
	}

}
