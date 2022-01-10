package com.cla.employeeportaldemo.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IDNotFoundExceptionHandler {
	@ExceptionHandler(value= {IDNotFoundException.class})
	public ResponseEntity<Object> habdlerNotFoundException(IDNotFoundException e){
		HttpStatus badRequest=HttpStatus.BAD_REQUEST;
		IDException empIDException=new IDException(
				e.getMessage(),
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("Z"))
				);
		return new ResponseEntity<>(empIDException,badRequest);
		
	}  
}
