package com.demo.ConsumerLoan.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ConsumerLoan.entity.Customer;
import com.demo.ConsumerLoan.entity.Employee;
import com.demo.ConsumerLoan.entity.Loan;
import com.demo.ConsumerLoan.repository.CustomerRepository;
import com.demo.ConsumerLoan.repository.EmployeeRepository;
import com.demo.ConsumerLoan.request.LoanApplication;

@Service
public class EmployeeService {
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public boolean isEmployeeExist(String empId) {
		if(this.employeeRepository.existsById(empId)) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public String approveLoan(LoanApplication application) throws Exception 
	{	
		Loan loan = loanService.getLoanByLoandAndCustomerId(application);
		Optional<Customer> optCust = customerRepository.findById(application.getCustomerId());
		if(optCust.isPresent()) 
		{	
			Optional<Employee> optEmp = employeeRepository.findById(application.getEmployeeId());
			if(optEmp.isPresent())
			{
				loan.setEmployee(optEmp.get());
				
				Customer customer = optCust.get();
				
				// validations
				
				if(loan.getLoanAmount() >= (customer.getAnnualIncome() * 10)) {
					loan.setRemarks("Loan amount cannot be 10 times of annual income");
					loan.setApproved(false);
				}
				/*
				 * else if(loan.getCollaterals() == null || loan.getCollaterals().size() == 0) {
				 * loan.setRemarks( "No collateral submitted"); loan.setApproved(false); }
				 */
				else if(customer.isItrAttached() == false) {
					loan.setRemarks( "Income proof not attached");
					loan.setApproved(false);
				}
				else if(customer.getIdentity().isEmpty()) {
					loan.setRemarks("Identity document not submitted");
					loan.setApproved(false);
				}
				else{
					loan.setRemarks( "Approved");
					loan.setApproved(true);
				}
				
				Loan savedLoan = this.loanService.updateLoan(loan);
				if(savedLoan.isApproved()) {
					return "Approved Loan: "+savedLoan.getId();  
				}
				else {
					return "Remarks: "+savedLoan.getRemarks();  
				}
			}
			else {
				throw new EntityNotFoundException("Invalid Employee ID");
			}
		}
		return "Error";
	}
}