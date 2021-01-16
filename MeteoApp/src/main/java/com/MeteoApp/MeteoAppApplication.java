package com.MeteoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import database.DataManagement;
import database.DataManagementImplementation;

@SpringBootApplication
public class MeteoAppApplication {

	private static DataManagement datamanagement = new  DataManagementImplementation();
	
	public static void main(String[] args) {
		datamanagement.downloadAndParseCities();
		datamanagement.downloadAndParseData();
		SpringApplication.run(MeteoAppApplication.class, args);
	}

}
