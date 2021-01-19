package com.MeteoApplication.Exception;

/**
 * Classe che rappresenta un'eccezione che viene lanciata quando l'operatore inserito in un filtro non è
 * valido o non è valido per quel campo
 * 
 * @author Juri Vitali
 *
 */
public class IllegalOperatorException extends InvalidFilterException {
	
	private static final long serialVersionUID = 4L; //codice per la deserializzazione
	
	private String messaggio;
	/**
	 * Costruttore senza messaggio
	 */
	public IllegalOperatorException() {
		super();
	}
	
	/**
	 * Costruttore con messaggio 
	 * 
	 * @param messaggio indica il messaggio che verrà stampato e che descrive la causa che ha portato al lancio dell'eccezione
	 */
	public IllegalOperatorException(String messaggio) {
		super(messaggio);
	}
	
	public IllegalOperatorException(String messaggio,String campo) {
		this.messaggio=messaggio;
		switch(campo) {
		case "temperature":
		case "tempPer":
		case "realTempAvg":
		case "realTempMin":
		case "realTempMax":
		case "realTempVariance":
		case "percTempAvg":
		case "percTempMin":
		case "percTempMax":
		case "percTempVariance": this.messaggio += " Gli operatori disponibili per il campo "+ campo +" sono: $bt, $gt e $lt"; break;
		case "period": this.messaggio += " Gli operatori disponibili per il campo period sono: $eq, $gt , $gte e $bt"; break;
		case "id": this.messaggio += "Per il campo id è disponibile l'operatore $eq.";
		}
	}

	public String getMessaggio() {
		return messaggio;
	}
	
	
}
