package com.demo.ConsumerLoan.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity
@Table(name = "Loan")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String type;
	private double loanAmount;
	private double intrestRate;
	private double period;
	private boolean isApproved;
	private String remarks;
	private String collateral;

	@ManyToOne
	@JoinColumn(name = "customerId", referencedColumnName = "id")
	private Customer customer;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "empId", referencedColumnName = "id")
	private Employee employee;

}
