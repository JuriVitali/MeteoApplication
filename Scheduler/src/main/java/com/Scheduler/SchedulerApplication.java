package com.Scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.service.WeatherService;
import com.service.WeatherServiceImplementation;


/**
 * Classe che contiene solamente il main e ha lo scopo di avvia lo scheduler e puo' resettare il file contenente i dati sulle temperature
 * 
 * @author Juri Vitali
 * @author Nicola Sebstianelli
 */
@SpringBootApplication
@EnableScheduling
public class SchedulerApplication {

	/**
	 * Il main avvia lo scheduler. Prima di farlo cancella tutti i dati dal file contenente i dati sulle temperature se gli si passa come 
	 * parametro (da riga di comando) la stringa reset
	 * 
	 * @param args questo parametro è opzionale. Se viene passata la stringa "reset" cancella tutti i dati dal file contenente  i dati sulle temperature
	 *            
	 */
	public static void main(String[] args) {
		if (args.length!=0 && args[0] == "reset") {
			WeatherService weatherService = new WeatherServiceImplementation() ;
			weatherService.update("",WeatherService.fileNameData);
		}
		SpringApplication.run(SchedulerApplication.class, args);
	}

}
