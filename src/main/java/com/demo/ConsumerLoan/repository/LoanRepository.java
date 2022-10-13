package com.demo.ConsumerLoan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.ConsumerLoan.entity.Customer;
import com.demo.ConsumerLoan.entity.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer> {

	@Query("select x from Loan x where x.customer=:customer and x.id=:loanId")
	public Optional<Loan> findByCustomerAndLoanId(Customer customer, int loanId);
	
}
