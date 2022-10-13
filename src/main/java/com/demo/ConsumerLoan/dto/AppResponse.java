package com.demo.ConsumerLoan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AppResponse {
	private Messages message;
	private String info;
}