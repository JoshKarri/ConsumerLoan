package com.demo.ConsumerLoan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.ConsumerLoan.entity.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
	
	@Query("select c from Customer c where c.emailId=:email")
	public Optional<Customer> findCustomerByEmailId(String email);

}
