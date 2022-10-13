package com.demo.ConsumerLoan.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerRegister {

	private String name;
	private String address;
	private String emailId;
	private String identity;
	private double annualIncome;
	private boolean itrAttached;
}
