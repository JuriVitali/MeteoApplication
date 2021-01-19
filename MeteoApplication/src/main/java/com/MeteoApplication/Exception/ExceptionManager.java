package com.MeteoApplication.Exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionManager {
	
	@ExceptionHandler( value = {InternalException.class})
 	public ResponseEntity<Object> handleInternalException(InternalException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, InternalException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.INTERNAL_SERVER_ERROR);
 	}
	
	@ExceptionHandler( value = {IllegalFieldException.class})
 	public ResponseEntity<Object> handleIllegalFieldException(IllegalFieldException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, IllegalFieldException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	@ExceptionHandler( value = {IllegalOperatorException.class})
 	public ResponseEntity<Object> handleIllegalOperatorException(IllegalOperatorException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, IllegalOperatorException.class.getCanonicalName(),
 											e.getMessaggio(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	@ExceptionHandler( value = {IllegalValueException.class})
 	public ResponseEntity<Object> handleIllegalValueException(IllegalValueException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, IllegalValueException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	@ExceptionHandler( value = {InvalidDateFormatException.class})
 	public ResponseEntity<Object> handleInvalidDateFormatException(InvalidDateFormatException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, InvalidDateFormatException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	@ExceptionHandler( value = {InvalidParametersException.class})
 	public ResponseEntity<Object> handleInvalidParametersException(InvalidParametersException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, InvalidParametersException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	@ExceptionHandler( value = {InvalidFilterException.class})
 	public ResponseEntity<Object> handleInvalidFilterException(InvalidFilterException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, InvalidFilterException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
}
