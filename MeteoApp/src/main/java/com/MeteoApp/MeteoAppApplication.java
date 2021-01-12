package com.MeteoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeteoAppApplication {

	public static void main(String[] args) {
		
		//invocazione del metodo che consente di effettuare la richiesta a Dropbox per scaricare il contenuto del file Data.json
		//e di parsare tale contenuto popolando un oggetto Vector di tipo Record 
		
		SpringApplication.run(MeteoAppApplication.class, args);
	}

}
