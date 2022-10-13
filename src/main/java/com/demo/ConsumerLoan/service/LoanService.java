package com.demo.ConsumerLoan.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ConsumerLoan.constants.Constants;
import com.demo.ConsumerLoan.entity.Customer;
import com.demo.ConsumerLoan.entity.Employee;
import com.demo.ConsumerLoan.entity.Loan;
import com.demo.ConsumerLoan.repository.EmployeeRepository;
import com.demo.ConsumerLoan.repository.LoanRepository;
import com.demo.ConsumerLoan.request.LoanApplication;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public String customerLoanApplication(LoanApplication application) {
		Loan loan = new Loan();

		Customer customer = this.customerService.findCustomerById(application.getCustomerId());
		loan.setCustomer(customer);

		Optional<Employee> optEmployee = this.employeeRepository.findById(application.getEmployeeId());
		if (optEmployee.isPresent()) {
			loan.setEmployee(optEmployee.get());
		} else {
			loan.setEmployee(null); // just until assigned
		}

		loan.setApproved(false);
		loan.setRemarks("unassigned");
		loan.setType(application.getLoanType());
		loan.setLoanAmount(application.getLoanAmount());
		loan.setPeriod(application.getPeriod());

		// Assuming input only non-negative & >0 values
		double calculatedIntered = Constants.calculateRate(application.getPeriod(), application.getPeriod());
		loan.setIntrestRate(calculatedIntered);

		if (!application.getCollateral().isEmpty()) {
			String collateralString = Constants.COLLATERAL_TYPE.stream()
					.filter(x -> x.getId() == application.getCollateral()).toString();
			if (!collateralString.isEmpty()) {
				loan.setCollateral(collateralString);
			} else {
				loan.setCollateral("0");
			}
		} else {
			loan.setCollateral("0");
		}

		Loan savedLoan = loanRepository.save(loan);
		return "Loan ID: " + savedLoan.getId();
	}

	public boolean isLoanExistsForCustomer(LoanApplication application) {
		if (this.loanRepository.existsById(application.getLoanId())) {
			return true;
		}
		return false;
	}

	public String uploadCollateral(LoanApplication application) {
		System.out.println("ID============> "+application.getLoanId());
		Optional<Loan> optLoan = this.loanRepository.findById(application.getLoanId());
		
		if(optLoan.isPresent()) {
			Loan loan = optLoan.get();
			
			String collateralString = Constants.COLLATERAL_TYPE.stream()
					.filter(x -> x.getId() == application.getCollateral()).toString();

			if (!collateralString.isEmpty()) {
				loan.setCollateral(collateralString);
			} else {
				loan.setCollateral("0");
			}

			Loan updatedLoan = this.loanRepository.save(loan);
			return "Collaterals for Loan: " + updatedLoan.getId() + " uploaded";
		}
		
		return "No Loans found";
	}

	
	  public Loan getLoanByLoandAndCustomerId(LoanApplication application) {
		  Customer customer = customerService.findCustomerById(application.getCustomerId());
		  Optional<Loan> optLoan = this.loanRepository.findByCustomerAndLoanId(customer, application.getLoanId()); 
		  return optLoan.orElseThrow(()->new EntityNotFoundException("No loans found with given information")); 
	  }
	 

	public Loan updateLoan(Loan loan) {
		return this.loanRepository.save(loan);
	}

}
