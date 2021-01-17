package com.database;

import java.util.Vector;

import com.model.City;
import com.model.Metadata;
import com.model.Record;

/**
 * Interfaccia che implementa dei metodi per gestire (scaricare,parsare e comunicare) i dati raccolti su un file Dropbox.
 * 
 * @author Nicola Sebastianelli
 *
 */
public interface DataManagement {
	
	/**
	* Vettore che all'avvio viene popolato con tutti i dati raccolti
	*/
	public static Vector<Record>  listaDati = new Vector<Record>();
	
	/**
	 * Vettore che all'avvio viene popolato con tutte le città di cui si sono raccolti i dati
	 */
	public static Vector<City>  elencoCitta = new Vector<City>();
	
	/**
	 * Il metodo getMetadata popola e poi restituisce un Vector di tipo Metadata con i metadati 
	 * 
	 * @return il Vector di tipo Metadata elencoMetadati contenente i metadati
	 */
	public Vector<Metadata> getMetadata();
	
	
	/**
	 * Il metodo getData restituisce il Vector di tipo Record listaDati contenente tutti i dati delle misurazioni
	 * 
	 * @return il Vector di tipo Record listaDati contenente tutti i dati delle misurazioni 
	 */
	public Vector<Record> getData();
	
	
	/**
	 * Il metodo downloadAndParseCities esegue una chiamata all'API di Dropbox per scaricare i dati delle misurazioni presenti sul file Data.json,
	 * memorizzato sull'account Dropbox.progettoesameoop@gmail.com
	 * Inoltre viene eseguito un parse dei dati e popolato un Vector di tipo Record listaDati
	 * 
	 */
	public void downloadAndParseData();
	
	/**
	 * Il metodo downloadAndParseCities esegue una chiamata all'API di Dropbox per scaricare i dati delle città presenti sul file CityList.json,
	 * memorizzato sull'account Dropbox.progettoesameoop@gmail.com
	 * Inoltre viene eseguito un parse dei dati e popolato un Vector di tipo City elencoCittà
	 * 
	 */
	public void downloadAndParseCities();
	
	/**
     * Il metodo getCities prende come argomento una stringa che deve essere ricercata e in base a essa
     * filtra il Vector di tipo City elencoCitta.
     * Infine restituisce un Vector di tipo City il cui nome delle città contiene la sottostringa.
     * 
     * @param sottostringa stringa che deve essere ricercata
     * @return restituisce un Vector di tipo City il cui nome delle città contiene la sottostringa
     */
	public  Vector<City> getCities(String sottostringa);
	
	/**
	 * Il metodo takeName prende come argomento l' id della citta' di cui deve essere trovato il nome e
	 * se presente restituisce il nome della citta'
	 * 
	 * @param id indica l' id della citta' di cui si vuole trovare il nome
	 * @return il nome della citta' se presente nel Vector di tipo City elencoCitta
	 */
	public String takeName(long id);
	
	/**
	 * Il metodo getLiveData effettua una chiamata all' API di OpenWeather per l' id che gli viene passato 
	 * come argomento. Inoltre popola un vettore di tipo Record con i dati restituiti dall'API mediante la chiamata.
	 * All'interno di questo metodo viene lanciato il metodo ottieniDataAtt.
	 * 
	 * @param id indica l' id della citta' di cui si vogliono conoscere i dati sulle temperature.
	 * @return un oggetto Vector di tipo Record con i dati sulle temperature della citta' in base all' id passato come argomento.
	 * @see model.City
	 * @see model.Record
	 */
	public Record getLiveData(long id);
	
}
