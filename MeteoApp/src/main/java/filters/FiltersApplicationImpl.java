package filters;

import java.util.Vector;

import model.City;
import model.Statistics;


public class FiltersApplicationImpl implements FiltersApplication{
	
	
	public Vector<City> reTempAvgIncl (Vector<Statistics> v, double min, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempAvg()<=max && s.getRealTempAvg()>=min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> reTempMinIncl (Vector<Statistics> v, double min, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempMin()<=max && s.getRealTempMin()>=min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> reTempMaxIncl (Vector<Statistics> v, double min, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempMax()<=max && s.getRealTempMax()>=min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> reTempVarIncl (Vector<Statistics> v, double min, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempVariance()<=max && s.getRealTempVariance()>=min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempAvgIncl (Vector<Statistics> v, double min, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempAvg()<=max && s.getPercTempAvg()>=min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempMinIncl (Vector<Statistics> v, double min, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempMin()<=max && s.getPercTempMin()>=min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempMaxIncl (Vector<Statistics> v, double min, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempMax()<=max && s.getPercTempMax()>=min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempVarIncl (Vector<Statistics> v, double min, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempVar()<=max && s.getPercTempVar()>=min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	
	public Vector<City> reTempAvgLess (Vector<Statistics> v, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempAvg()<max) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}

	public Vector<City> reTempMinLess (Vector<Statistics> v, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempMin()<max) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> reTempMaxLess (Vector<Statistics> v, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempMax()<max) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> reTempVarLess (Vector<Statistics> v, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempVariance()<max) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempAvgLess (Vector<Statistics> v, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempAvg()<max) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}

	public Vector<City> percTempMinLess (Vector<Statistics> v, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempMin()<max) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempMaxLess (Vector<Statistics> v, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempMax()<max) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempVarLess (Vector<Statistics> v, double max) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempVar()<max) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}

	
	public Vector<City> reTempAvgGreat (Vector<Statistics> v, double min) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempAvg()>min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> reTempMinGreat (Vector<Statistics> v, double min) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempMin()>min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> reTempMaxGreat (Vector<Statistics> v, double min) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempMax()>min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> reTempVarGreat (Vector<Statistics> v, double min) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getRealTempVariance()>min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempAvgGreat (Vector<Statistics> v, double min) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempAvg()>min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempMinGreat (Vector<Statistics> v, double min) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempMin()>min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempMaxGreat (Vector<Statistics> v, double min) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempMax()>min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}
	
	public Vector<City> percTempVarGreat (Vector<Statistics> v, double min) {
		Vector<City> risposta = new Vector<City>();
		for (Statistics s: v) if (s.getPercTempVar()>min) risposta.add(new City(s.getId(), s.getName())); 
		return risposta;
	}


}
