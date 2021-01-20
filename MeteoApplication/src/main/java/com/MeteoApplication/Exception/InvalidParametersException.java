package com.MeteoApplication.Exception;

/**
 * InvalidParametersException e' un'eccezione che viene lanciata quando i dati inseriti tra i parametri di una richiesta
 * non sono validi.
 * 
 * @author Juri Vitali
 *
 */
public class InvalidParametersException extends RuntimeException{
	
	private static final long serialVersionUID = 2L; //codice per la deserializzazione
	
	/**
	 * Costruttore senza messaggio
	 */
	public InvalidParametersException() {
		super();
	}
	
	/**
	 * Costruttore con messaggio 
	 * 
	 * @param messaggio indica il messaggio che verrà stampato e che descrive la causa che ha portato al lancio dell'eccezione
	 */
	public InvalidParametersException(String messaggio) {
		super(messaggio);
	}
}
