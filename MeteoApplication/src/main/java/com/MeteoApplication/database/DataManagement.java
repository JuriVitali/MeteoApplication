package com.MeteoApplication.database;

import java.util.Vector;

import com.MeteoApplication.Exception.InternalException;
import com.MeteoApplication.model.City;
import com.MeteoApplication.model.Metadata;
import com.MeteoApplication.model.Record;

/**
 * Interfaccia che implementa dei metodi per gestire (scaricare,parsare e comunicare) i dati raccolti su un file Dropbox.
 * 
 * @author Nicola Sebastianelli
 *
 */
public interface DataManagement {
	
	/**
	 * Campo che contiene il nome del file che contiene il dataset
	 */
	public String nomeFileDati = "Data.json";
	
	/**
	 * Campo che contiene il nome del file che contiene la lista delle citta' di cui si hanno i dati sulle temperature
	 */
	public String nomeFileCitta = "CityList.json";
	
	/**
	* Vettore che all'avvio viene popolato con tutti i dati raccolti
	*/
	public static Vector<Record>  listaDati = new Vector<Record>();
	
	/**
	 * Vettore che all'avvio viene popolato con tutte le città di cui si sono raccolti i dati
	 */
	public static Vector<City>  elencoCitta = new Vector<City>();
	
	/**
	 * Il metodo getMetadata popola e poi restituisce un Vector di tipo {@link com.MeteoApplication.model.Metadata  Metadata} con i metadati 
	 * 
	 * @return il Vector di tipo {@link com.MeteoApplication.model.Metadata  Metadata} contenente i metadati dell'applicazione
	 */
	public Vector<Metadata> getMetadata();
	
	
	/**
	 * Il metodo getData restituisce un Vector di tipo {@link com.MeteoApplication.model.Record  Record} contenente tutti i dati delle misurazioni
	 * 
	 * @return il Vector di tipo {@link com.MeteoApplication.model.Record  Record} contenente tutti i dati delle misurazioni .
	 */
	public Vector<Record> getData();
	
	
	/**
	 * Il metodo downloadAndParseCities esegue una chiamata all'API di Dropbox per scaricare i dati delle misurazioni presenti sul 
	 * file {@link com.MeteoApplication.database.DataManagement#nomeFileDati il cui nome e' contenuto in nomeFileDati},
	 * memorizzato sull'account Dropbox.progettoesameoop@gmail.com
	 * Inoltre viene eseguito un parse dei dati e popolato un Vector di tipo {@link com.MeteoApplication.model.Record  Record} 
	 */
	public void downloadAndParseData() ;
	
	/**
	 * Il metodo downloadAndParseCities esegue una chiamata all'API di Dropbox per scaricare i dati delle città presenti sul file 
	 * {@link com.MeteoApplication.database.DataManagement#nomeFileCitta il cui nome e' contenuto in nomeFileCitta},memorizzato sull'account Dropbox.progettoesameoop@gmail.com
	 * Inoltre viene eseguito un parse dei dati e popolato un Vector di tipo {@link com.MeteoApplication.model.City  City} 
	 */
	public void downloadAndParseCities() ;
	
	/**
     * Il metodo getCities prende come argomento una stringa che deve essere ricercata e in base a essa
     * filtra il Vector di tipo {@link com.MeteoApplication.model.City  City} contenente tutte le citta' disponibili.
     * Infine restituisce un Vector di tipo {@link com.MeteoApplication.model.City  City} il cui nome delle città contiene la sottostringa.
     * 
     * @param sottostringa/stringa che deve essere ricercata
     * @return restituisce un Vector di tipo {@link com.MeteoApplication.model.City  City} il cui nome delle città contiene la sottostringa
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
	 * come argomento. Inoltre popola un vettore di tipo {@link com.MeteoApplication.model.Record  Record} con i dati restituiti dall'API mediante la chiamata.
	 * All'interno di questo metodo viene lanciato il metodo {@link com.MeteoApplication.util.DateOperations#ottieniDataAtt(long) ottieniDataAtt }
	 * 
	 * @param id indica l' id della citta' di cui si vogliono conoscere i dati sulle temperature.
	 * @return un oggetto Vector di tipo Record con i dati sulle temperature della citta' in base all' id passato come argomento.
	 * @throws InternalException se si verifica un'errore nella chiamata all'API o nella lettura o nel parsing della risposta.
	 * @see com.MeteoApplication.model.City
	 * @see com.MeteoApplication.model.Record
	 */
	public Record getLiveData(long id) throws InternalException;
	
}
