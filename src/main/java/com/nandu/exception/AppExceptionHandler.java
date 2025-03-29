package com.nandu.exception;

import java.time.LocalDateTime;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nandu.contoller.AccountRestController;

/* 
 * ANY AXCEPTION OCCUR IN OUR APP SEND THAT EXCEPTION DETAILS TO THE CLIENT
 * this function is responsible for "AppException" information
 * specify exMsg : this is description
 * ceate AppException class obj and set data 
 * create response obj with AppExceptionClass obj (what body we have to send (exception) and statusCode : internal_server_error)
 * 
 */
@RestControllerAdvice
public class AppExceptionHandler {
	
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);
	
	
	// when runtime exception occure execute this class
	// @@ExceptionHandler : for whcih exception we need to execute whoch method we need to use this annotation
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<AppException> handleException(String exMsg){
		
		logger.error("Error occur : " + exMsg);		
		AppException ex = new AppException();
		ex.setExCode("EX0003");// every exception have diff code 
		ex.setExDesc(exMsg);
		ex.setExDate(LocalDateTime.now());
		return new ResponseEntity<AppException>(ex , HttpStatus.INTERNAL_SERVER_ERROR);
	} 

}
