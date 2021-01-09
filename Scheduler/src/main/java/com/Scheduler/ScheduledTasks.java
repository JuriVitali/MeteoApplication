package com.Scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import model.City;
import model.Record;
import service.*;
import java.util.Vector;
//import org.springframework.beans.factory.annotation.Autowired;

/**
 * La classe è un componte che implementa uno schedulatore e fa uso dell'interfaccia WeatherService
 * 
 * @author Juri Vitali 
 * @author Nicola Sebastianelli 
 * @see WeatherService
 */
@Component
public class ScheduledTasks {
	private int i=0;
	private Vector<City> cities;
	private WeatherService weatherService = new WeatherServiceImplementation() ;
	private Vector<Record> lista;
	/**
	 * Metodo che viene eseguito ogni ora e che serve per raccogliere informazioni sulle temperature registrate in alcune città su un file
	 * json che si trova in un account Dropbox. Le città (nome e id) di cui vengono raccolti i dati sono prese da un file contenuto nel 
	 * medesimo account Dropbox. 
	 *  <p>
	 *  In particolare questo metodo:
	 *  - scarica le informazioni contenute nel file CityList.json (in Dropbox) riguardante le città (questa operazione viene eseguita solo la prima 
	 *    volta che ile metodo viene eseguito;
	 *  - esegue una chiamata ad OpenWeather per ogni città e fa il parsing delle informazioni ottenute;
	 *  - scarica le informazioni contenute nel file Data.json (in Dropbox) riguardanti i dati precedentemente raccolti;
	 *  - unisce tali informazioni con i dati raccollti mediante le chiamtate avvenute durante questa esecuzione
	 *  - sovrascrive il file Data.json 
	 *  Per eseguire tali operazioni il metodo fa uso dell'interfaccia WeatherService
	 *  
	 *  @see WeatherService
	 */
	/*@Scheduled (fixedRate = 15000) //ogni 15 secondi
	public void ReportData() {
		if (i==0) {cities=weatherService.getCities((weatherService.download(WeatherService.fileNameCities))); i++;};	
		String totalData = weatherService.download(WeatherService.fileNameData);
		for(Record v : weatherService.parse(cities)) totalData += weatherService.produceString(v);
		weatherService.update(totalData, WeatherService.fileNameData);
	}    */
	
	@Scheduled (fixedRate = 15000) //ogni 15 secondi
	public void ReportData() {
		i++;
		if (i==1) {
		String s=weatherService.download(WeatherService.fileNameCities);
		cities=weatherService.getCities(s); 	
		 }
		String totalData = '['+weatherService.download(WeatherService.fileNameData);
		lista=weatherService.parse(cities);
		for(Record v : lista) {
			if (i==1 && v==lista.get(0)) totalData += weatherService.produceString(v); 
			else totalData += " , "+weatherService.produceString(v);
		}
		totalData += ']';
		System.out.println(totalData);
		weatherService.update(totalData, WeatherService.fileNameData);
	}  
	
	
}
