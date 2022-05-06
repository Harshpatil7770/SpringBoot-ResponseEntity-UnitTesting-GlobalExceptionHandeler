package com.xoriant.ecart.globalexceptionhandeler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandeler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserInputExpection.class)
	public ResponseEntity<String> inputUserException(UserInputExpection exception) {
		return new ResponseEntity<String>("Please Check Input", HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<String> elementNotFoundException(ElementNotFoundException exception){
		return new ResponseEntity<String>("Element Not Found",HttpStatus.OK);
		
	}
}
