package com.MeteoApplication.Exception;

/**
 * Classe che rappresenta un'eccezione che viene lanciata quando una data inserita dall'utente (nel body
 * della richiesta o come parametro) non rispetta il formato richiesto
 * 
 * @author Juri Vitali
 *
 */
public class InvalidDateFormatException extends RuntimeException{
	
	private static final long serialVersionUID = 5L; //codice per la deserializzazione
	
	/**
	 * Costruttore senza messaggio
	 */
	public InvalidDateFormatException() {
		super();
	}
	
	/**
	 * Costruttore con messaggio 
	 * 
	 * @param messaggio indica il messaggio che verrà stampato e che descrive la causa che ha portato al lancio dell'eccezione
	 */
	public InvalidDateFormatException(String messaggio) {
		super(messaggio);
	}
}
