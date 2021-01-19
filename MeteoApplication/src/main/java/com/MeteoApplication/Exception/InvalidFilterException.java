package com.MeteoApplication.Exception;

public class InvalidFilterException extends RuntimeException{
	
	private static final long serialVersionUID = 7L;
	
	/**
	 * Costruttore senza messaggio
	 */
	public InvalidFilterException() {
		super();
	}
	
	/**
	 * Costruttore con messaggio 
	 * 
	 * @param messaggio indica il messaggio che verrà stampato e che descrive la causa che ha portato al lancio dell'eccezione
	 */
	public InvalidFilterException(String messaggio) {
		super(messaggio);
	}
}
