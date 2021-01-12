package com.Scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Classe che contiene solamente il main e ha lo scopo di avviare lo scheduler
 * 
 * @author Juri Vitali
 * @author Nicola Sebstianelli
 */
@SpringBootApplication
@EnableScheduling
public class SchedulerApplication {

	/**
	 * Il main avvia lo scheduler            
	 */
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}
}
