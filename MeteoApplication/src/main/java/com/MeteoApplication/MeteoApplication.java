package com.MeteoApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.MeteoApplication.Exception.InternalException;
import com.MeteoApplication.database.DataManagement;
import com.MeteoApplication.database.DataManagementImplementation;

@SpringBootApplication
public class MeteoApplication {
	
	private static DataManagement datamanagement = new  DataManagementImplementation();

	public static void main(String[] args) throws InternalException {
		datamanagement.downloadAndParseCities();
		datamanagement.downloadAndParseData();
		SpringApplication.run(MeteoApplication.class, args);
	}

}
