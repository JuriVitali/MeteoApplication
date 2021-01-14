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
import database.DataManagement;

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
	
	@RequestMapping(value = "getId", method = RequestMethod.GET)
	public Vector<City> getCityName(@RequestParam (name="name") String sottostringa) {
		return DataManagement.getCities(sottostringa);
	}
	
	@RequestMapping(value = "stats", method = RequestMethod.POST)
	public Statistics getStats(@RequestParam (name="id") long id) {
		//chiama filtro per id
		Vector<Record> vettoreFiltrato = new Vector<Record>(); //in verità dovrà prendere il risultato della chiamata al filtro
		if (vettoreFiltrato.isEmpty())/*lancia un'eccezione*/;
		String name=DataManagement.takeName(id);
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
	
	@RequestMapping(value = "filterStats", method = RequestMethod.POST)
	public Vector<City> StatsFilter(@RequestParam (name ="periodStart") String inizio, @RequestParam (name = "periodEnd") String fine, 
			@RequestBody Object filtro) {
		//chiamo metodo per trasformare le date in un oggetto di tipo data
		//uso direttamente il filtro per le date
		Vector<Statistics> stats = new Vector<Statistics>(); 
		for (City c : DataManagement.elencoCitta) {
			//filtro il vettore ottenuto in precedenza prendendo solo i record relativi alla città c
			//aggiungo a stats un elemento passando al costruttore di stats l'id di c,il nome di c e il vettore direcord appena ottenuto.
		}    
		//chiamo un metodo per il parsing del body e che gestisce l'applicazione dei vari filtri su un vector di Statistics
		//restituisco un vector di city
		return null;
	}
	
	
}
