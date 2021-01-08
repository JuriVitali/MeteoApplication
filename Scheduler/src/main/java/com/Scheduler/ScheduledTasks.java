package com.Scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import model.City;
import service.WeatherService;
import java.util.Vector;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class ScheduledTasks {
	private int i=0;
	private Vector<City> cities;
	
	@Autowired
	WeatherService weatherService;
	
	@Scheduled (fixedRate = 15000) //ogni 15 secondi
	public void ReportData() {
		if (i==0) cities=weatherService.getCities((weatherService.download(WeatherService.fileNameCities)));
		weatherService.parse(cities);	
		String totalData = weatherService.produceString() + weatherService.download(WeatherService.fileNameData);
		weatherService.update(totalData, WeatherService.fileNameData);
	}    
}
