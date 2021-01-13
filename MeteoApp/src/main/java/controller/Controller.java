package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import database.DataManagement;

import java.util.Vector;
import model.Record;
import model.Metadata;
import model.City;
import model.Statistics;

@RestController
public class Controller {
	
	@RequestMapping(value = "metadata", method=RequestMethod.GET)
	public Vector<Metadata> getVectorMetadata() {
		return DataManagement.getMetadata();
	}
	
	@RequestMapping(value = "data", method=RequestMethod.GET)
	public Vector<Record> getAllData() {
		return DataManagement.getData();
	}
	
	@RequestMapping(value = "name", method = RequestMethod.GET)
	public Vector<City> getName() {
		return DataManagement.getCities();
	}
	
	@RequestMapping(value = "stats", method = RequestMethod.POST)
	public Statistics getStats(@RequestParam (name="id") long id) {
		//chiama filtro per id
		Vector<Record> vettoreFiltrato = new Vector<Record>(); //in verità dovrà prendere il risultato della chiamata al filtro
		String name=DataManagement.takeName(id);
		//if( (name=DataManagement.takeName(id)) == null ) throw ...    controlla se l'id è valido. Se no lancia un'eccezione
		//chiama metodo 0
		//controllare se il vettore filtrato è vuoto
		Statistics stats = new Statistics (id, name, vettoreFiltrato);
		return stats;
	}
	
	@RequestMapping(value = "liveTemp", method = RequestMethod.POST)
	public Vector<Record> getTemps(@RequestBody Object fitro) {
		//chiama metodo 0 (vedere alla fine se occorre implementare un altro metodo)
		//controlla se il vettore filtrato è vuoto
		//per ogni elemento del vettore filtrato chiama un metodo che esegue una chiamata all'API di OpenWeather.
		//Ogni elemento di tipo Record restituito da tale metodo viene aggiunto al vettore di record che alla fine va restituito
		return null;
	}
	
	@RequestMapping(value = "cities", method = RequestMethod.POST)
	public Vector<City> CityFilter(@RequestBody Object filtro) {
		//chiama metodo 0 
		//restituisce un vettore di City con le città contenute nella risposta di metodo 0
		return null;
	}
	
}
