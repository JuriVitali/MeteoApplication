package com.model;

/**
 * Classe che descrive i dati riguardanti la temperatura rilevati in una citta'. 
 * 
 * @author Juri Vitali
 * @author Nicola Sebastianelli
 */
public class Record extends City{
	private double temperature;
	private double tempPer;
	private double tempMax;
	private double tempMin;
	private String time;
	
	/**
	 * Costruttore che inizializza tutti i campi dell'oggetto con gli argomenti passatigli.
	 * 
	 * @param id ID della citta'
	 * @param name nome della citta'
	 * @param temperature temperatura generale
	 * @param tempPer temperatura percepita
	 * @param tempMax temperatura massima all'interno della citta'
	 * @param tempMin temperatura minima all'interno della citta'
	 * @param time data e ora in cui è avvenuta la misurazione
	 */
	public Record(long id, String name, double temperature, double tempPer, double tempMax, double tempMin , String time) {
		super(id, name);
		this.temperature = temperature;
		this.tempPer = tempPer;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.time=time;
	}
	
	/**
	 * Costruttore senza parametri che inizializza tutti i dati sulle temperature e id a 0; invece nome e tempo in 
	 * cui viene effettuata la misura vengono inizializzati con una stringa vuota.
	 */
	public Record() {
		super(0, "");
		temperature=0;
		tempPer=0;
		tempMax=0;
		tempMin=0;
		time="";
	}

	//getters e setters
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getTempPer() {
		return tempPer;
	}

	public void setTempPer(double tempPer) {
		this.tempPer = tempPer;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
	
	
}
