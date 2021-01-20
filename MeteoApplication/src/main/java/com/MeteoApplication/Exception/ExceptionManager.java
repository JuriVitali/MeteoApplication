package com.MeteoApplication.Exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe che gestisce a livello globale le eccezioni che l'applicazione puo' generare
 * 
 * @author Juri Vitali
 * @see com.MeteoApplication.Exception.IllegalFieldException
 * @see com.MeteoApplication.Exception.IllegalOperatorException
 * @see com.MeteoApplication.Exception.IllegalValueException
 * @see com.MeteoApplication.Exception.InternalException
 * @see com.MeteoApplication.Exception.InvalidFilterException
 * @see com.MeteoApplication.Exception.InvalidParametersException
 * @see com.MeteoApplication.Exception.InvalidDateFormatException
 */
@ControllerAdvice
public class ExceptionManager {
	
	/**
	 * Metodo che gestisce un'eccezione di tipo InternalException restituendo come risposta un oggetto di tipo ResponseEntity
	 * che contiene le informazioni sull'eccezione verificatasi
	 * 
	 * @param e eccezione di tipo InternalException che è stata lanciata
	 * @return un oggetto di tipo ResponseEntity che contiene le informazioni sull'eccezione verificatasi
	 * @see com.MeteoApplication.Exception.InternalException
	 */
	@ExceptionHandler( value = {InternalException.class})
 	public ResponseEntity<Object> handleInternalException(InternalException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, InternalException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.INTERNAL_SERVER_ERROR);
 	}
	
	
	
	/**
	 * Metodo che gestisce un'eccezione di tipo IllegalFieldException restituendo come risposta un oggetto di tipo ResponseEntity
	 * che contiene le informazioni sull'eccezione verificatasi
	 * 
	 * @param e eccezione di tipo IllegalFieldException che è stata lanciata
	 * @return un oggetto di tipo ResponseEntity che contiene le informazioni sull'eccezione verificatasi
	 * @see com.MeteoApplication.Exception.IllegalFieldException
	 */
	@ExceptionHandler( value = {IllegalFieldException.class})
 	public ResponseEntity<Object> handleIllegalFieldException(IllegalFieldException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, IllegalFieldException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	
	
	/**
	 * Metodo che gestisce un'eccezione di tipo IllegalOperatorException restituendo come risposta un oggetto di tipo ResponseEntity
	 * che contiene le informazioni sull'eccezione verificatasi
	 * 
	 * @param e eccezione di tipo IllegalOperatorException che è stata lanciata
	 * @return un oggetto di tipo ResponseEntity che contiene le informazioni sull'eccezione verificatasi
	 * @see com.MeteoApplication.Exception.IllegalOperatorException
	 */
	@ExceptionHandler( value = {IllegalOperatorException.class})
 	public ResponseEntity<Object> handleIllegalOperatorException(IllegalOperatorException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, IllegalOperatorException.class.getCanonicalName(),
 											e.getMessaggio(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	
	
	/**
	 * Metodo che gestisce un'eccezione di tipo IllegalValueException restituendo come risposta un oggetto di tipo ResponseEntity
	 * che contiene le informazioni sull'eccezione verificatasi
	 * 
	 * @param e eccezione di tipo IllegalValueException che è stata lanciata
	 * @return un oggetto di tipo ResponseEntity che contiene le informazioni sull'eccezione verificatasi
	 * @see com.MeteoApplication.Exception.IllegalValueException
	 */
	@ExceptionHandler( value = {IllegalValueException.class})
 	public ResponseEntity<Object> handleIllegalValueException(IllegalValueException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, IllegalValueException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	
	
	/**
	 * Metodo che gestisce un'eccezione di tipo InvalidDateFormatException restituendo come risposta un oggetto di tipo ResponseEntity
	 * che contiene le informazioni sull'eccezione verificatasi
	 * 
	 * @param e eccezione di tipo InvalidDateFormatException che è stata lanciata
	 * @return un oggetto di tipo ResponseEntity che contiene le informazioni sull'eccezione verificatasi
	 * @see com.MeteoApplication.Exception.InvalidDateFormatException
	 */
	@ExceptionHandler( value = {InvalidDateFormatException.class})
 	public ResponseEntity<Object> handleInvalidDateFormatException(InvalidDateFormatException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, InvalidDateFormatException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	
	
	/**
	 * Metodo che gestisce un'eccezione di tipo InvalidParametersException restituendo come risposta un oggetto di tipo ResponseEntity
	 * che contiene le informazioni sull'eccezione verificatasi
	 * 
	 * @param e eccezione di tipo InvalidParametersException che è stata lanciata
	 * @return un oggetto di tipo ResponseEntity che contiene le informazioni sull'eccezione verificatasi
	 * @see com.MeteoApplication.Exception.InvalidParametersException
	 */
	@ExceptionHandler( value = {InvalidParametersException.class})
 	public ResponseEntity<Object> handleInvalidParametersException(InvalidParametersException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, InvalidParametersException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
	
	
	
	/**
	 * Metodo che gestisce un'eccezione di tipo InvalidFilterException restituendo come risposta un oggetto di tipo ResponseEntity
	 * che contiene le informazioni sull'eccezione verificatasi
	 * 
	 * @param e eccezione di tipo InvalidFilterException che è stata lanciata
	 * @return un oggetto di tipo ResponseEntity che contiene le informazioni sull'eccezione verificatasi
	 * @see com.MeteoApplication.Exception.InvalidFilterException
	 */
	@ExceptionHandler( value = {InvalidFilterException.class})
 	public ResponseEntity<Object> handleInvalidFilterException(InvalidFilterException e){
 		
 		ExceptionModel eccezione = new ExceptionModel(HttpStatus.BAD_REQUEST, InvalidFilterException.class.getCanonicalName(),
 											e.getMessage(),  Instant.now());
 		return new ResponseEntity<>(eccezione, HttpStatus.BAD_REQUEST);
 	}
}
