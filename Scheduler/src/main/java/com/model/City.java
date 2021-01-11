package com.model;

/**
 * Classe che descrive una citta' atrraverso due campi: uno rappresenta il nome e uno l'ID.
 * 
 * @author Juri Vitali
 * @author Nicola Sebastianelli
 *
 */
public class City {
	long id;
	String name;
	
	/**
	 * Costruttore che inizializza l'oggeto con l'ID e il nome che gli vengono passati come argomenti.
	 * 
	 * @param id rappresenta l'ID della citta'.
	 * @param name indica il nome della citta'.
	 */
	public City(long id, String name) {
		this.id = id;
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String toString() {
		return "City [id=" + id + ", name=" + name + "]";
	}	
	
	
}
