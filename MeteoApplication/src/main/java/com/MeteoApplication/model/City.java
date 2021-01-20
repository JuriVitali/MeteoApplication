package com.MeteoApplication.model;

/**
 * La classe City rappresenta una citta' in base al nome e all'id
 * 
 * @author Juri Vitali
 * @author Nicola Sebastianelli
 * @author Roberto Palladino
 * 
 */
public class City {
	private long id;
	private String name;
	
	/**
	 * Costruttore
	 * 
	 * @param id indica l'id della citta'.
	 * @param name indica il nome della citta'.
	 */
	public City(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
