package com.MeteoApplication.Exception;

/**
 * Classe che rappresenta un'eccezione lanciata quando il campo inserito in un filtro non è valido per la rotta
 * 
 * @author Juri Vitali
 *
 */
public class IllegalFieldException extends InvalidFilterException {
	
	private static final long serialVersionUID = 3L; //codice per la deserializzazione
	
	/**
	 * Costruttore senza messaggio
	 */
	public IllegalFieldException() {
		super();
	}
	
	/**
	 * Costruttore con messaggio 
	 * 
	 * @param messaggio indica il messaggio che verrà stampato e che descrive la causa che ha portato al lancio dell'eccezione
	 */
	public IllegalFieldException(String messaggio) {
		super(messaggio);
	}
	
}
