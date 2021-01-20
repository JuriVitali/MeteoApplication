package com.MeteoApplication.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MeteoApplication.Exception.IllegalFieldException;
import com.MeteoApplication.Exception.IllegalOperatorException;
import com.MeteoApplication.Exception.IllegalValueException;
import com.MeteoApplication.Exception.InternalException;
import com.MeteoApplication.Exception.InvalidFilterException;
import com.MeteoApplication.Exception.InvalidParametersException;
import com.MeteoApplication.database.DataManagement;
import com.MeteoApplication.database.DataManagementImplementation;
import com.MeteoApplication.filters.RecordFilterManagement;
import com.MeteoApplication.filters.StatisticsFilterManagement;

import java.util.Vector;
import com.MeteoApplication.model.Record;
import com.MeteoApplication.model.Metadata;
import com.MeteoApplication.model.City;
import com.MeteoApplication.model.Data;
import com.MeteoApplication.model.Statistics;
import com.MeteoApplication.util.DateOperations;

/**
 * Il controller contiene una serie di metodi che rispondono ciascuno ad una diversa rotta.
 * Alcuni di essi fanno uso dell'interfaccia {@link com.MeteoApplication.database.DataManagement  DataManagement}, 
 * mentre altri utilizzano il metodo parseBody della classe {@link com.MeteoApplication.filters.RecordFilterManagement  RecordFilterManagement}
 * o della classe {@link com.MeteoApplication.filters.StatisticsFilterManagement  StatisticsFilterManagement}.
 * 
 * @author Juri Vitali
 * 
 */
@RestController
public class Controller {
	
	private DataManagement datamanagement = new  DataManagementImplementation();
	
	/**
	 * Il metodo getVectorMetadata risponde alla richiesta 
	 * "metadata" e restituisce un Vector di tipo {@link com.MeteoApplication.model.Metadata  Metadata} 
	 * contenente i metadati del programma.
	 * Utilizza il metodo {@link com.MeteoApplication.database.DataManagement#getMetadata()  getMetadata}
	 * dell'interfaccia DataManagement.
	 * 
	 * @return un Vector di tipo {@link com.MeteoApplication.model.Metadata  Metadata} 
	 * contenente i metadati del programma.
	 */
	@RequestMapping(value = "metadata", method=RequestMethod.GET)
	public Vector<Metadata> getVectorMetadata() {
		return datamanagement.getMetadata();
	}
	
	
	
	/**
	 * Il metodo getAllData risponde alla richiesta "data" e restituisce un Vector di tipo {@link com.MeteoApplication.model.Record  Record} 
	 * contente tutti i dati raccolti sulle temperature.
	 * Utilizza il metodo {@link com.MeteoApplication.database.DataManagement#getData()  getData} dell'interfaccia DataManagement.
	 * 
	 * @return un Vector di tipo {@link com.MeteoApplication.model.Record  Record}  contente tutti i dati 
	 * raccolti sulle temperature
	 */
	@RequestMapping(value = "data", method=RequestMethod.GET)
	public Vector<Record> getAllData() {
		return datamanagement.getData();
	}
	
	
	
	/**
	 * Il metodo getCityName risponde alla richiesta "getId" e restituisce un Vector di tipo 
	 * {@link com.MeteoApplication.model.City  City} contenente nome e id delle città il cui nome contiene la stringa
	 * passata come parametro.
	 * Utilizza il metodo {@link com.MeteoApplication.database.DataManagement#getCities(String)  getCities} 
	 * dell'interfaccia DataManagement.
	 *  
	 * @param sottostringa indica il nome o parte del nome della/e citta' di cui si vuole conoscere l'id.
	 * @return un Vector di tipo {@link com.MeteoApplication.model.City  City} contenente nome e id delle città il cui 
	 * nome contiene la stringa passata come parametro.
	 */
	@RequestMapping(value = "getId", method = RequestMethod.GET)
	public Vector<City> getCityName(@RequestParam (name="name") String sottostringa) {
		return datamanagement.getCities(sottostringa);
	}
	
	
	
	/**
	 * Il metodo getStats, dati l'id di una città e un filtro su un periodo temporale, consente di ottenere le
	 * statistiche sulle temperature registrate in tale citta' nel periodo selezionato.
	 * 
	 * @param id indica l'id della citta' di cui si vogliono ottenere le statistiche.
	 * @param filtro rappresenta il filtro (formato JSON) che si vuole al periodo nel quale si vogliono calcolare le statistiche.
	 * @return un oggetto di tipo {@link com.MeteoApplication.model.Statistics  Statistics}
	 * @throws InvalidFilterException viene lanciata se la sintassi del filtro è errata
	 * @throws IllegalValueException viene lanciata se ci sono valori non coerenti
	 * @throws IllegalOperatorException viene lanciata se ad un campo viene associato un operatore non valido
	 * @throws IllegalFieldException viene lanciata se viene immesso nel filtro un campo non valido
	 * @throws InvalidParametersException se l'id non corrisponde a nessuna delle citta' disponibili
	 */
	@RequestMapping(value = "stats", method = RequestMethod.POST)    
	public Statistics getStats(@RequestParam (name="id") long id, @RequestBody String filtro)
	  throws InvalidFilterException, IllegalValueException, IllegalOperatorException, IllegalFieldException, InvalidParametersException{
		
		Vector<Record> vettoreFiltrato = new Vector<Record>(); 
		
		//selezione dei record relativi alla città con id passato come parametro
		for (Record r: DataManagement.listaDati) if(r.getId()==id) vettoreFiltrato.add(new Record(r));  
		String name=datamanagement.takeName(id);
		
		//controlla se l'id inserito corrisponde ad una delle citta' di cui sono disponibili le misurazioni
		if (name == null) throw new InvalidParametersException ("L'id immesso non corrisponde ad alcuna città registrata.");
		
		//filtraggio rispetto al periodo
		vettoreFiltrato = RecordFilterManagement.parseBody(filtro, vettoreFiltrato, 4);  
		if (vettoreFiltrato.isEmpty()) return new Statistics ( id , "Nessuna misurazione nel periodo selezionato" , null);
		
		//istanzia un oggetto contenente nome, id e statistiche della città (che vengono calcolate automaticamente in base alle misurazioni)
		Statistics stats = new Statistics (id, name, vettoreFiltrato);   
		return stats;
	}
	
	
	/**
	 * Il metodo liveTemp restituisce i dati in tempo reale sulla temperatura nella/e citta' cui corrispondono gli id passati.
	 * Utilizza il metodo {@link com.MeteoApplication.database.DataManagement#getLiveData(long)  getLiveData} dell'interfaccia DataManagement.
	 * 
	 * @param filtro indica il filtro (formato JSON) sugli id delle citta' di cui si vogliono ottenere i dati
	 * @return un array di tipo Record con i dati attuali sulla temperatura delle città selezionate
	 * @throws InternalException viene lanciata se si verifica un problema nella chiamata all'API di OpenWeather, nella lettura della risposta di tale chiamata o nel parsing della risposta
	 * @throws InvalidFilterException viene lanciata se la sintassi del filtro è errata
	 * @throws IllegalValueException viene lanciata se ci sono valori non coerenti
	 * @throws IllegalOperatorException viene lanciata se ad un campo viene associato un operatore non valido
	 * @throws IllegalFieldException viene lanciata se viene immesso nel filtro un campo non valido
	 */
	@RequestMapping(value = "liveTemp", method = RequestMethod.POST) 
	public Vector<Record> getTemps(@RequestBody String filtro) throws InternalException, 
	  InvalidFilterException, IllegalValueException, IllegalOperatorException, IllegalFieldException {
		
		//filtra record in base all'id
		Vector<Record> vettoreFiltrato = RecordFilterManagement.parseBody(filtro, DataManagement.listaDati, 5);  
		Vector<City> cittaFiltrate = new Vector<City>();
		
		//popola il vettore cittaFiltrate con le città che rispondo ad un certo id
		for (Record v : vettoreFiltrato) cittaFiltrate.add(new City(v.getId(),v.getName()));    
		Vector<Record> risposta = new Vector<Record>();
		
		//Per ogni citta' che soddisfa il filtro sull'id viene chiamato getLiveData che effettua una chiamata all'API
		//di OpenWeather e restituisce un Vector di Record contenente le informazioni desiderate
		for (City c : cittaFiltrate) risposta.add(datamanagement.getLiveData(c.getId())); 
		return risposta;
	}
	
	
	
	/**
	 * Il metodo CityFilter risponde alla richiesta "cities". Dato un filtro contenente valori sulla temperatura reale, percepita e
	 * sul periodo , esso restituisce un array di tipo  {@link com.MeteoApplication.model.City  City} che contiene
	 * le citta' che rispettano i vincoli immessi nel  periodo desiderato.
	 * 
	 * @param filtro consiste in un filtro (formato JSON) sulla temperatura, temperatura percepita e periodo
	 * @return un array di tipo  {@link com.MeteoApplication.model.City  City} che contiene le citta' che rispettano i vincoli immessi nel  periodo desiderato.
	 * @throws InvalidFilterException viene lanciata se la sintassi del filtro è errata
	 * @throws IllegalValueException viene lanciata se ci sono valori non coerenti
	 * @throws IllegalOperatorException viene lanciata se ad un campo viene associato un operatore non valido
	 * @throws IllegalFieldException viene lanciata se viene immesso nel filtro un campo non valido
	 */
	@RequestMapping(value = "cities", method = RequestMethod.POST)  
	public Vector<City> CityFilter(@RequestBody String filtro) throws InvalidFilterException,
	  IllegalValueException, IllegalOperatorException, IllegalFieldException {
		
		//filtra il vettore con tutti i dati
		Vector<Record> vettoreFiltrato = RecordFilterManagement.parseBody(filtro, DataManagement.listaDati, 6); 
		
		//si prendono le citta' che dispongono di misurazioni che rispettano tutti i filtri
		Vector<City> cittaFiltrate = new Vector<City>();
		for (Record v : vettoreFiltrato) cittaFiltrate.add(new City(v.getId(),v.getName()));  
		return cittaFiltrate;
	}
	
	
	/**
	 * il metodo StatsFilter risponde alla chiamata "filterStats" e restituisce un array di tipo  
	 * {@link com.MeteoApplication.model.City  City} contenente le citta' che rispettano i vincoli imposti sulle statistiche
	 * calcolate in un periodo temporale scelto. 
	 * 
	 * @param inizio indica la data di inizio del periodo temporale su cui si vogliono calcolare le statistiche
	 * @param fine indica la data di fine del periodo temporale su cui si vogliono calcolare le statistiche
	 * @param filtro consiste in un filtro (formato JSON) contenente i vincoli sulle statistiche
	 * @return un array di tipo {@link com.MeteoApplication.model.City  City} contenente le citta' che rispettano i vincoli imposti
	 * @throws InvalidFilterException viene lanciata se la sintassi del filtro è errata
	 * @throws IllegalValueException viene lanciata se ci sono valori non coerenti
	 * @throws IllegalOperatorException viene lanciata se ad un campo viene associato un operatore non valido
	 * @throws IllegalFieldException viene lanciata se viene immesso nel filtro un campo non valido
	 * @throws InvalidParametersException se la data di inizio del periodo è successiva a quella di fine del periodo
	 */
	@RequestMapping(value = "filterStats", method = RequestMethod.POST)
	public Vector<City> StatsFilter(@RequestParam (name ="periodStart") String inizio, @RequestParam (name = "periodEnd") String fine, 
			@RequestBody String filtro) throws InvalidParametersException, InvalidFilterException,
	                IllegalValueException, IllegalOperatorException, IllegalFieldException {
		
		//Trasformazione delle stringhe contenenti le date in oggetto di tipo data
		Data begin = new Data(DateOperations.getGiorno(inizio), DateOperations.getMese(inizio), DateOperations.getAnno(inizio));
		Data end = new Data(DateOperations.getGiorno(fine), DateOperations.getMese(fine), DateOperations.getAnno(fine));
		
		//verifica che la seconda sia maggiore della prima. Altrimenti lancia un'eccezione
		if (DateOperations.confrontaDate(begin,end)==-1) throw new InvalidParametersException("La data di fine del periodo inserita"
				+ " è antecedente a quella di inizio del periodo");
		Vector<Record> arrayFiltrato = new Vector<Record>();
		
		//selezione delle misurazioni avvenute nel periodo relativamente al quale si desiderano sapere le statistiche
		for (Record r : DataManagement.listaDati) 
			if( (DateOperations.confrontaDate(r.getData(),begin)==-1 || DateOperations.confrontaDate(r.getData(),begin)==0) &&
					(DateOperations.confrontaDate(r.getData(),end)==1 || DateOperations.confrontaDate(r.getData(),end)==0) )
				arrayFiltrato.add(new Record(r));
		Vector<Statistics> stats = new Vector<Statistics>(); 
		Vector<Record> misurazioniCitta = new Vector<Record>();
		
		//per ogni città viene creato un vettore di stats prendendo in considerazione solo le misurazioni avvenute nel periodo scelto
		for (City c : DataManagementImplementation.elencoCitta) {
			misurazioniCitta.removeAllElements();
			for (Record r : arrayFiltrato) if (c.getId()==r.getId()) misurazioniCitta.add(r); 
			stats.add(new Statistics(c.getId(),c.getName(),misurazioniCitta));
		}    
		
		//chiamata di un metodo per il parsing del body e che gestisce l'applicazione dei vari filtri su un vector di Statistics
		Vector<City> cittaFiltrate = StatisticsFilterManagement.parseBody(filtro,stats);
		return cittaFiltrate;
	}
	
	
}
