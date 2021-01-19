package com.MeteoApplication.Exception;

import java.io.IOException;

/**
 * Classe che rappresenta un'eccezione lanciata quando si verificano problemi con le chiamate ad un API o
 * con il download, la lettura o il parsing del dataset
 * 
 * @author Juri Vitali
 *
 */

public class InternalException extends IOException {
	
	private static final long serialVersionUID = 1L; //codice per la deserializzazione
	
	/**
	 * Costruttore senza messaggio
	 */
	public InternalException() {
		super();
	}
	
	/**
	 * Costruttore con messaggio 
	 * 
	 * @param messaggio indica il messaggio che verrà stampato e che descrive la causa che ha portato al lancio dell'eccezione
	 */
	public InternalException(String messaggio) {
		super(messaggio);
	}
}
