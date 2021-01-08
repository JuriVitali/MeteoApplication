package service;

import java.util.Vector;

import model.City;
import org.springframework.stereotype.Service;

@Service
public interface WeatherService {
	String fileNameData="Data";
	String fileNameCities="CityList.json";
	
	abstract Vector<City> getCities(String data);
	abstract String download(String nomeFile);
	abstract void update(String newData, String nomeFile);
	abstract void parse(Vector<City> lista);
	abstract String produceString();
}
