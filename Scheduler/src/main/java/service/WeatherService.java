package service;

import java.util.Vector;

import model.*;

/**
 * L'interfaccia implementa tutti quei metodi utili allo schedulatore per raccogliere nuovi dati e aggiornare quelli raccolti
 * 
 * @author Juri Vitali 
 * @author Nicola Sebastianelli
 */

public interface WeatherService {
	String fileNameData="/Data.json";
	String fileNameCities="/CityList.json";
	
	/**
	 * Il metodo getCities prende come parametro una stringa data stringa contenente dati di città (id e nome) in formato json e restituisce 
	 * un vettore di tipo City contenente i dati delle città che erano stati passati come parametro sottoforma di stringa.
	 * 
	 * @param data stringa contenente dati di città (id e nome) in formato json
	 * @return un vettore di tipo City contenente i dati delle città che erano stati passati come parametro sottoforma di stringa
	 */
	abstract Vector<City> getCities(String data);
	
	
	/**
	 * Il metodo download prende come parametro il nomeFile nome del file memorizzato sull'account Dropbox 
	 * progettoesameoop@gmail.com di cui si vuole esaminare il contenuto e attraverso una chiamata all'API di Dropbox, restituisce 
	 * il contenuto del file sottoforma di stringa.
	 * 
	 * @param nomeFile nome del file memorizzato sull'account Dropbox progettoesameoop@gmail.com di cui si vuole esaminare il contenuto
	 * @return il contenuto del file sottoforma di stringa
	 */
	abstract String download(String nomeFile);
	
	/**
	 * 
	 * @param newData
	 * @param nomeFile
	 */
	abstract void update(String newData, String nomeFile);
	
	/**
	 * 
	 * @param lista
	 * @return
	 */
	abstract Vector<model.Record> parse(Vector<City> lista);
	
	/**
	 * 
	 * @return
	 */
	abstract String produceString(model.Record r);
}
