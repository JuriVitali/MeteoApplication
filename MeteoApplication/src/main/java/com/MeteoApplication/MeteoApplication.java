package com.MeteoApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.MeteoApplication.database.DataManagement;
import com.MeteoApplication.database.DataManagementImplementation;

/**
 * Classe che ha come unico metodo il main, da dove parte il programma
 * 
 * @author Juri Vitali
 * @author Nicola Sebastianelli 
 * @author Roberto Palladino
 */
@SpringBootApplication
public class MeteoApplication {
	
	private static DataManagement datamanagement = new  DataManagementImplementation();

	/**
	 * Il metodo main scarica da due file caricati su Dropbox rispettivamente la lista delle citta' di cui si hanno i
	 * dati sulle temperature e il dataset con i dati raccolti mediante lo schedulatore (per far cio' usa l'interfaccia
	 * DataManagement). 
	 * Successivamente avvia i componenti dell'applicazione.
	 * 
	 * @param args eventuali argomenti inseriti da riga di comando
	 * @see com.MeteoApplication.database.DataManagement
	 */
	public static void main(String[] args) {
		datamanagement.downloadAndParseCities();         //scarica la lista delle città
		datamanagement.downloadAndParseData();           //scarica i dati
		SpringApplication.run(MeteoApplication.class, args);
	}

}
