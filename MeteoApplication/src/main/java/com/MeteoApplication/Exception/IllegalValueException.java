package com.MeteoApplication.Exception;

/**
 * Classe che rappresenta un'eccezione che viene lanciata quando il valore inserito dall'utente (tra i parametri o nel
 * body della richiesta) non è valido
 * 
 * @author Juri Vitali
 *
 */
public class IllegalValueException extends InvalidFilterException {
	
	private static final long serialVersionUID = 6L; //codice per la deserializzazione
	
	/**
	 * Costruttore senza messaggio
	 */
	public IllegalValueException() {
		super();
	}
	
	/**
	 * Costruttore con messaggio 
	 * 
	 * @param messaggio indica il messaggio che verrà stampato e che descrive la causa che ha portato al lancio dell'eccezione
	 */
	public IllegalValueException(String messaggio) {
		super(messaggio);
	}
}
