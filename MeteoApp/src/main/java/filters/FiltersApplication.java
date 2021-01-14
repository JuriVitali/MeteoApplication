package filters;

import model.Statistics;
import model.City;

import java.util.Vector;

public interface FiltersApplication {
	//filtri su data
	
	//filtri per temperatura
	
	//filtri per temperatura percepita
	
	//filtri per statistiche :devono prendere come parametri , il/i
	// valori di riferimento e il vector di statistics da filtrare
	//Devono restituire un Vector di city filtrato
	public Vector<City> reTempAvgIncl (Vector<Statistics> v, double min, double max);
	public Vector<City> reTempMinIncl (Vector<Statistics> v, double min, double max);
	public Vector<City> reTempMaxIncl (Vector<Statistics> v, double min, double max);
	public Vector<City> reTempVarIncl (Vector<Statistics> v, double min, double max);
	public Vector<City> percTempAvgIncl (Vector<Statistics> v, double min, double max);
	public Vector<City> percTempMinIncl (Vector<Statistics> v, double min, double max);
	public Vector<City> percTempMaxIncl (Vector<Statistics> v, double min, double max);
	public Vector<City> percTempVarIncl (Vector<Statistics> v, double min, double max);
	
	public Vector<City> reTempAvgLess (Vector<Statistics> v, double max);
	public Vector<City> reTempMinLess (Vector<Statistics> v, double max);
	public Vector<City> reTempMaxLess (Vector<Statistics> v, double max);
	public Vector<City> reTempVarLess (Vector<Statistics> v, double max);
	public Vector<City> percTempAvgLess (Vector<Statistics> v, double max);
	public Vector<City> percTempMinLess (Vector<Statistics> v, double max);
	public Vector<City> percTempMaxLess (Vector<Statistics> v, double max);
	public Vector<City> percTempVarLess (Vector<Statistics> v, double max);
	
	public Vector<City> reTempAvgGreat (Vector<Statistics> v, double min);
	public Vector<City> reTempMinGreat (Vector<Statistics> v, double min);
	public Vector<City> reTempMaxGreat (Vector<Statistics> v, double min);
	public Vector<City> reTempVarGreat (Vector<Statistics> v, double min);
	public Vector<City> percTempAvgGreat (Vector<Statistics> v, double min);
	public Vector<City> percTempMinGreat (Vector<Statistics> v, double min);
	public Vector<City> percTempMaxGreat (Vector<Statistics> v, double min);
	public Vector<City> percTempVarGreat (Vector<Statistics> v, double min);

}
