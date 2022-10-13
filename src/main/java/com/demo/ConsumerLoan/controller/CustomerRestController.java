package com.demo.ConsumerLoan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ConsumerLoan.request.CustomerRegister;
import com.demo.ConsumerLoan.request.LoanApplication;
import com.demo.ConsumerLoan.service.CustomerService;
import com.demo.ConsumerLoan.service.LoanService;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LoanService loanService;
	
	// Dint validate any inputs
	
	@PostMapping("/register")
	public int registerCustomer(@RequestBody CustomerRegister customer) {
		return customerService.addCustomer(customer);
	}
	
	@PostMapping("/applyForLoan")
	public String applyForLoan(@RequestBody LoanApplication application) {
		if(this.customerService.isCustomerExists(application.getCustomerId())){
			return this.loanService.customerLoanApplication(application);
		}
		else {
			return "Customer not registered";
		}
	}
	
	@PostMapping("/updateCollateral")
	public String updateCollateral(@RequestBody LoanApplication application) 
	{
		if(this.customerService.isCustomerExists(application.getCustomerId())) {
			if(this.loanService.isLoanExistsForCustomer(application)) {
				return this.loanService.uploadCollateral(application);
			}
			else {
				return "No Loans found on Customer";
			}
		}
		else {
			return "Customer not registered";
		}
	}
	
	@PostMapping("/uploadItr")
	public String uploadItr(@RequestBody LoanApplication application) 
	{
		if(this.customerService.isCustomerExists(application.getCustomerId())) {
			return this.customerService.uploadItr(application);
		}
		else {
			return "Customer not registered";
		}
	}

}
