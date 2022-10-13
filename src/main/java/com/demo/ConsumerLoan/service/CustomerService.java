package com.demo.ConsumerLoan.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ConsumerLoan.entity.Customer;
import com.demo.ConsumerLoan.repository.CustomerRepository;
import com.demo.ConsumerLoan.request.CustomerRegister;
import com.demo.ConsumerLoan.request.LoanApplication;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findCustomerByEmail(String emailId) {
		Optional<Customer> optCustomer = customerRepository.findCustomerByEmailId(emailId);
		return optCustomer.orElseThrow(()->new EntityNotFoundException("Customer with "+ emailId +" not found"));
	}
	
	public Customer findCustomerById(int customerId) {
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		return optCustomer.orElseThrow(()->new EntityNotFoundException("Customer with "+ customerId +" not found"));
	}
	
	public boolean isCustomerExists(int customerId) {
		if(this.customerRepository.existsById(customerId))
		{
			return true;
		}
		return false;
	}
	
	@Transactional
	public int addCustomer(CustomerRegister cr) {
		Customer customer = new Customer();
		
		customer.setName(cr.getName());
		customer.setAddress(cr.getAddress());
		customer.setAnnualIncome(cr.getAnnualIncome());
		customer.setEmailId(cr.getEmailId());
		customer.setIdentity(cr.getIdentity());
		customer.setItrAttached(cr.isItrAttached());
		
		Customer save = this.customerRepository.save(customer);
		return save.getId();
	}
	
	public String uploadItr(LoanApplication application) {
		Optional<Customer> optCustomer = this.customerRepository.findById(application.getCustomerId());
		if(optCustomer.isPresent()) {
			Customer customer = optCustomer.get();
			customer.setItrAttached(application.isItrSubmitted());
			this.customerRepository.save(customer);
			return "uploaded ITR";
		}
		return "Error";
	}
	
}
