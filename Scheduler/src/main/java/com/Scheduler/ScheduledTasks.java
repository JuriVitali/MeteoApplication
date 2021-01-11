package com.Scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.model.City;
import com.model.Record;
import com.service.*;
import java.util.Vector;
//import org.springframework.beans.factory.annotation.Autowired;

/**
 * La classe e' un componente che implementa uno schedulatore e fa uso dell'interfaccia WeatherService
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
	 * Metodo che viene eseguito ogni ora e che serve per raccogliere informazioni sulle temperature registrate in alcune citta' su un file
	 * json che si trova in un account Dropbox. Le citta' (nome e id) di cui vengono raccolti i dati sono prese da un file contenuto nel 
	 * medesimo account Dropbox. 
	 *  <p>
	 *  In particolare questo metodo:
	 *  - scarica le informazioni contenute nel file CityList.json (in Dropbox) riguardante le città (questa operazione viene eseguita solo la prima 
	 *    volta che ile metodo viene eseguito;
	 *  - esegue una chiamata ad OpenWeather per ogni citta' e fa il parsing delle informazioni ottenute;
	 *  - scarica le informazioni contenute nel file Data.json (in Dropbox) riguardanti i dati precedentemente raccolti;
	 *  - unisce tali informazioni con i dati raccolti mediante le chiamate avvenute durante questa esecuzione;
	 *  - sovrascrive il file Data.json .
	 *  Per eseguire tali operazioni il metodo fa uso dell'interfaccia WeatherService.
	 *  
	 *  @see com.service.WeatherService
	 */

	@Scheduled (fixedRate = 3600000) //ogni ora
	public void ReportData() {
		String totalData = new String();
		if (i==0) {
			cities=weatherService.getCities(weatherService.download(WeatherService.fileNameCities)); 
			totalData += '['; 
			i++;
		}
		totalData += weatherService.download(WeatherService.fileNameData) + "\n\n";
		lista=weatherService.parse(cities);
		for(Record v : lista) 
			if (i==1 && v==lista.get(0)) totalData += weatherService.produceString(v); 
			else totalData += " , "+weatherService.produceString(v);
		totalData += ']';
		weatherService.update(totalData, WeatherService.fileNameData);
	}  
	
	
}
