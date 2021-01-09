package com.Scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Classe che contiene solamente il main, il cui scopo è quello di avvviare l'unico componente consistente nella classe Scheduled Tasks
 * 
 * @author Juri Vitali
 * @author Nicola Sebstianelli
 */
@SpringBootApplication
@EnableScheduling
public class SchedulerApplication {

	/**
	 * il cui scopo è quello di avvviare l'unico componente, consistente nella classe Scheduled Tasks
	 */
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class);
	}

}
