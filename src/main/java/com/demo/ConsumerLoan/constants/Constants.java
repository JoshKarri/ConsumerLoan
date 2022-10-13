package com.demo.ConsumerLoan.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.demo.ConsumerLoan.entity.Collateral;

public class Constants {
	
	public static final List<Collateral> COLLATERAL_TYPE = 
		    Collections.unmodifiableList(
		    		Arrays.asList(
			    		new Collateral("1", "Vehicle Registration"),
			    		new Collateral("2", "House Doucments"),
			    		new Collateral("3", "Insurance Documents"),
			    		new Collateral("4", "Fixed Deposit")
			    		)
		    		); 
	
	public static final List<String> LOAN_TYPE = Arrays.asList(
			"Home",
			"Marriage",
			"Education",
			"Hospitalization"
			);
	
	public static final String ID_TYPE = "" ;
	public static final String LOAN_TABLE = "" ;
	public static final String CUSTOMER_TABLE = "" ;
	public static final String EMPLOYEE_TABLE = "" ;
	public static final String COLLATERAL_TABLE = "" ;
	public static final String LOAN_COLLATERAL_TABLE = "" ;


	public static double calculateRate(double amount, double period) {
		double interestRate = 0;
		if(period <= 0) {
			System.out.println("INVALID PERIOD");
		}
		if(period < 2) {
			interestRate = amount * (5/100);
		}
		else if(period >= 2 && period <= 5) {
			interestRate = amount * (6/100);
		}
		else if(period > 5 && period <= 8) {
			interestRate = amount * (8/100);
		}
		else if(period > 8) {
			interestRate = amount * (8.5/100);
		}
        return interestRate; // incomplete
    }
}
