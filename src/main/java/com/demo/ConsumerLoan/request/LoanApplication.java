package com.demo.ConsumerLoan.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoanApplication {
	
	private int customerId;
	private int loanId;
	private String employeeId;
	private String loanType;
	private double loanAmount;
	private double period;
	private String collateral;
	private boolean itrSubmitted;
}
