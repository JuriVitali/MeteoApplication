package com.MeteoApplication.Exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;

/**
 * Classe che costituisce il modello della risposta ricevuta dall'utente nel caso del verificarsi di un'eccezione
 * 
 * @author Juri Vitali
 * 
 */
public class ExceptionModel {
	
	private final HttpStatus httpstatus;
	private final String error;
	private final String messagge;
	private final Instant time;
	
	public ExceptionModel(HttpStatus httpstatus, String error, String messagge, Instant time) {
		this.httpstatus = httpstatus;
		this.error = error;
		this.messagge = messagge;
		this.time = time;
	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return messagge;
	}

	public Instant getTime() {
		return time;
	}
	
}