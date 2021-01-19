package com.MeteoApplication.controller;

import org.json.simple.parser.ParseException;
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

@RestController
public class Controller {
	
	private DataManagement datamanagement = new  DataManagementImplementation();
	
	
	@RequestMapping(value = "metadata", method=RequestMethod.GET)
	public Vector<Metadata> getVectorMetadata() {
		return datamanagement.getMetadata();
	}
	
	
	
	@RequestMapping(value = "data", method=RequestMethod.GET)
	public Vector<Record> getAllData() {
		return datamanagement.getData();
	}
	
	
	
	@RequestMapping(value = "getId", method = RequestMethod.GET)
	public Vector<City> getCityName(@RequestParam (name="name") String sottostringa) {
		return datamanagement.getCities(sottostringa);
	}
	
	
	
	@RequestMapping(value = "stats", method = RequestMethod.POST)    
	public Statistics getStats(@RequestParam (name="id") long id, @RequestBody String filtro)
	  throws InvalidFilterException, IllegalValueException, IllegalOperatorException, IllegalFieldException{
		
		Vector<Record> vettoreFiltrato = new Vector<Record>(); 
		for (Record r: DataManagement.listaDati) if(r.getId()==id) vettoreFiltrato.add(new Record(r));  //selezione dei record relativi alla città con id passato come parametro
		String name=datamanagement.takeName(id);
		if (name == null) return new Statistics (id,"Nessuna città corrisponde all'id selezionato",null);
		
		vettoreFiltrato = RecordFilterManagement.parseBody(filtro, vettoreFiltrato, 4);  //filtraggio rispetto al periodo
		if (vettoreFiltrato.isEmpty()) return new Statistics ( id , "Nessuna misurazione nel periodo selezionato" , null);
	
		Statistics stats = new Statistics (id, name, vettoreFiltrato);   //si istanzia un oggetto contenente nome, id e statistiche della città
		return stats;
	}
	
	
	
	@RequestMapping(value = "liveTemp", method = RequestMethod.POST) 
	public Vector<Record> getTemps(@RequestBody String filtro) throws InternalException, 
	  InvalidFilterException, IllegalValueException, IllegalOperatorException, IllegalFieldException {
		
		Vector<Record> vettoreFiltrato = RecordFilterManagement.parseBody(filtro, DataManagement.listaDati, 5);  //filtra record in base all'id
		Vector<City> cittaFiltrate = new Vector<City>();
		for (Record v : vettoreFiltrato) cittaFiltrate.add(new City(v.getId(),v.getName()));    //popola il vettore cittaFiltrate con le città che rispondo ad un certo id
		Vector<Record> risposta = new Vector<Record>();
		/*Per ogni citta' che soddisfa il filtro sull'id viene chiamato getLiveData che effettua una chiamata all'API
		 *di OpenWeather e restituisce un Vector di Record contenente le informazioni desiderate
		*/
		for (City c : cittaFiltrate) risposta.add(datamanagement.getLiveData(c.getId())); 
		return risposta;
	}
	
	
	
	@RequestMapping(value = "cities", method = RequestMethod.POST)  
	public Vector<City> CityFilter(@RequestBody String filtro) throws ParseException {
		Vector<Record> vettoreFiltrato = RecordFilterManagement.parseBody(filtro, DataManagement.listaDati, 6); //filtra il vettore con tutti i dati
		
		//si prendono le citta' che dispongono di misurazioni che rispettano tutti i filtri
		Vector<City> cittaFiltrate = new Vector<City>();
		for (Record v : vettoreFiltrato) cittaFiltrate.add(new City(v.getId(),v.getName()));  
		return cittaFiltrate;
	}
	
	
	
	@RequestMapping(value = "filterStats", method = RequestMethod.POST)
	public Vector<City> StatsFilter(@RequestParam (name ="periodStart") String inizio, @RequestParam (name = "periodEnd") String fine, 
			@RequestBody String filtro) throws InvalidParametersException, ParseException {
		
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
