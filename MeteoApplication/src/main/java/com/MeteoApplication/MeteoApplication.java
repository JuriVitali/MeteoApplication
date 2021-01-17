package com.MeteoApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.database.DataManagement;
import com.database.DataManagementImplementation;

@SpringBootApplication
public class MeteoApplication {
	
	private static DataManagement datamanagement = new  DataManagementImplementation();

	public static void main(String[] args) {
		datamanagement.downloadAndParseCities();
		datamanagement.downloadAndParseData();
		System.out.println(DataManagement.listaDati);
		SpringApplication.run(MeteoApplication.class, args);
	}

}
