package com.service;

import java.util.Vector;

import com.model.*;

/**
 * L'interfaccia implementa tutti quei metodi utili allo schedulatore per raccogliere nuovi dati e aggiornare quelli raccolti.
 * 
 * @author Juri Vitali 
 * @author Nicola Sebastianelli
 */

public interface WeatherService {
	String fileNameData="/Data.json";
	String fileNameCities="/CityList.json";
	
	/**
	 * Il metodo getCities restituisce un oggetto Vector di tipo City contenente i dati delle citta' (id e nome in formato json) 
	 * che sono stati passati come argomento sottoforma di stringa.
	 * 
	 * @param data stringa contenente dati di citta' (id e nome) in formato json.
	 * @return un oggetto Vector di tipo City contenente i dati delle citta' che erano stati passati come parametro sottoforma di stringa.
	 * @see com.model.City
	 */
	abstract Vector<City> getCities(String data);
	
	
	/**
	 * Il metodo download prende come argomento il nome del file memorizzato sull'account Dropbox. 
	 * progettoesameoop@gmail.com di cui si vuole esaminare il contenuto e attraverso una chiamata all'API di Dropbox, restituisce 
	 * il contenuto del file sottoforma di stringa.
	 * 
	 * @param nomeFile nome del file memorizzato sull'account Dropbox progettoesameoop@gmail.com di cui si vuole esaminare il contenuto.
	 * @return il contenuto del file sottoforma di stringa.
	 */
	abstract String download(String nomeFile);
	
	/**
	 * update sovrascrive il contenuto del file di cui si passa il nome ( che si trova sull'account Dropbox 
	 * progettoesameoop@gmail.com ) con la stringa passatagli.
	 * 
	 * @param newData stringa che si vuole scrivere sul file.
	 * @param nomeFile indica il nome del file che si vuole sovrascrivere.
	 */
	abstract void update(String newData, String nomeFile);
	
	/**
	 * Il metodo parse effettua una chiamata all' API di OpenWeather per ogni elemento del vettore di tipo City che gli viene passato 
	 * come argomento. Inoltre popola un vettore di tipo Record con i dati restituiti dall'API mediante tali chiamate.
	 * 
	 * @param lista indica l'oggetto Vector di tipo City che contiene nome e id delle citta' di cui si vogliono conoscere i dati sulle temperature.
	 * @return un oggetto Vector di tipo Record con i dati sulle temperature delle citta' contenute nell' oggetto che gli è stato passato come argomento.
	 * @see com.model.City
	 * @see com.model.Record
	 */
	abstract Vector<com.model.Record> parse(Vector<City> lista);
	
	/**
	 * Il metodo produceString restituisce sottoforma di stringain formato JSON i dati contenuti nell'argomento passatogli.
	 * 
	 * @param r indica l'oggetto di tipo Record che si vuole convertire in stringa (formato JSON).
	 * @return una stringa che contiene i dati in formato JSON relativi all'oggetto passatogli.
	 * @see com.model.Record
	 */
	abstract String produceString(com.model.Record r);
}
