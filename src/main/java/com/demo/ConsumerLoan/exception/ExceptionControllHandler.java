package com.demo.ConsumerLoan.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.ConsumerLoan.dto.AppResponse;
import com.demo.ConsumerLoan.dto.Messages;


@ControllerAdvice
public class ExceptionControllHandler {
	
	public ExceptionControllHandler() {
		System.out.println("Exception Handler Class");
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<AppResponse> handleEntityNotFoundException(EntityNotFoundException ex){
		System.out.println("exception occured");
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new AppResponse(Messages.FAILURE, ex.getMessage()));
	}
}
