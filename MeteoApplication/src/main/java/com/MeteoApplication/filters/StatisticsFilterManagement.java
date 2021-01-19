package com.MeteoApplication.filters;

import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.MeteoApplication.model.City;
import com.MeteoApplication.model.Statistics;

public class StatisticsFilterManagement {
	private static FiltersApplication app = new FiltersApplicationImpl();
	
	public static Vector<City> parseBody(String filtro, Vector<Statistics> notFilteredArray) throws ParseException {
		Vector<City> filteredArray = new Vector<City>();
		JSONParser parser = new JSONParser();
		JSONObject body = (JSONObject) parser.parse(filtro);
		if(body.containsKey("and")) {
			JSONArray elencoFiltri = (JSONArray) body.get("and"); 
			filteredArray = andMultipleFilterApplicator(elencoFiltri, notFilteredArray);
		}
		else if(body.containsKey("or")) {
			JSONArray elencoFiltri = (JSONArray) body.get("or"); 
			filteredArray = orMultipleFilterApplicator(elencoFiltri, notFilteredArray);
		}
			else {
				Vector<Statistics> filteredStatArray = new Vector<Statistics>();
				filteredStatArray = singleFilterApplicator(body,notFilteredArray);
				for(Statistics s: filteredStatArray) filteredArray.add(new City(s.getId(),s.getName()));
			}
		return filteredArray;
	}
	
	
	private static Vector<City> orMultipleFilterApplicator (JSONArray filtri, Vector<Statistics> notFilteredArray){
		Vector<City> finalArray = new Vector<City>();
		Vector<City> filteredCitiesArray = new Vector<City>();
		Vector<Statistics> filteredStatArray = new Vector<Statistics>();
		JSONObject filtro = new JSONObject();
		for (Object o : filtri) {
			filtro = (JSONObject) o;
			filteredStatArray = singleFilterApplicator(filtro,notFilteredArray);
			for(Statistics s: filteredStatArray) filteredCitiesArray.add(new City(s.getId(),s.getName()));
			finalArray.removeAll(filteredCitiesArray);
			finalArray.addAll(filteredCitiesArray);
		}
		return finalArray;
	}
	
	
	private static Vector<City> andMultipleFilterApplicator (JSONArray filtri, Vector<Statistics> notFilteredArray){
		Vector<Statistics> filteredArray = notFilteredArray;
		Vector<City> finalArray = new Vector<City>(); 
		JSONObject filtro = new JSONObject();
		for (Object o : filtri) {
			filtro = (JSONObject) o;
			filteredArray = singleFilterApplicator(filtro,filteredArray);
		}
		for(Statistics s: filteredArray) finalArray.add(new City(s.getId(),s.getName()));
		return finalArray;
	}
	
	private static Vector<Statistics> singleFilterApplicator (JSONObject filtro, Vector<Statistics> notFilteredArray){
		String campo="";
		Iterator<String> iterator = filtro.keySet().iterator();
        campo = (String) iterator.next();
		String operatore="";
		JSONObject interno = (JSONObject)filtro.get(campo);
		Iterator<String> iterator2 = interno.keySet().iterator();
        operatore = (String) iterator2.next();
		Vector<Statistics> filteredStats = new Vector<Statistics>();
		switch (operatore) {
			case "$gt": 
				double min = (double) interno.get(operatore);
				switch (campo) {
					case "ReTempAvg":filteredStats = app.reTempAvgGreat(notFilteredArray, min);break;
					case "ReTempMax":filteredStats = app.reTempMaxGreat(notFilteredArray, min);break;
					case "ReTempMin":filteredStats = app.reTempMinGreat(notFilteredArray, min);break;
					case "ReTempVariance":filteredStats = app.reTempVarGreat(notFilteredArray, min);break;
					case "PercTempAvg":filteredStats = app.percTempAvgGreat(notFilteredArray, min);break;
					case "PercTempMax":filteredStats = app.percTempMaxGreat(notFilteredArray, min);break;
					case "PercTempMin":filteredStats = app.percTempMinGreat(notFilteredArray, min);break;
					case "PercTempVariance":filteredStats = app.percTempVarGreat(notFilteredArray, min);break;
					default: //campo non valido
				}
				break;
				
			case "$lt":
				double max = (double) interno.get(operatore);
				switch (campo) {
					case "ReTempAvg":filteredStats = app.reTempAvgLess(notFilteredArray, max);break;
					case "ReTempMax":filteredStats = app.reTempMaxLess(notFilteredArray, max);break;
					case "ReTempMin":filteredStats = app.reTempMinLess(notFilteredArray, max);break;
					case "ReTempVariance":filteredStats = app.reTempVarLess(notFilteredArray, max);break;
					case "PercTempAvg":filteredStats = app.percTempAvgLess(notFilteredArray, max);break;
					case "PercTempMax":filteredStats = app.percTempMaxLess(notFilteredArray, max);break;
					case "PercTempMin":filteredStats = app.percTempMinLess(notFilteredArray, max);break;
					case "PercTempVariance":filteredStats = app.percTempVarLess(notFilteredArray, max);break;
					default: //campo non valido
				} 
				break;
			
			case "$bt":
				double[] param = RecordFilterManagement.getBtDoubleValues (interno);
				switch (campo) {
					case "ReTempAvg":filteredStats = app.reTempAvgIncl(notFilteredArray, param[0], param[1]);break;
					case "ReTempMax":filteredStats = app.reTempMaxIncl(notFilteredArray, param[0], param[1]);break;
					case "ReTempMin":filteredStats = app.reTempMinIncl(notFilteredArray, param[0], param[1]);break;
					case "ReTempVariance":filteredStats = app.reTempVarIncl(notFilteredArray, param[0], param[1]);break;
					case "PercTempAvg":filteredStats = app.percTempAvgIncl(notFilteredArray, param[0], param[1]);break;
					case "PercTempMax":filteredStats = app.percTempMaxIncl(notFilteredArray, param[0], param[1]);break;
					case "PercTempMin":filteredStats = app.percTempMinIncl(notFilteredArray, param[0], param[1]);break;
					case "PercTempVariance":filteredStats = app.percTempVarIncl(notFilteredArray, param[0], param[1]);break;
					default: //campo non valido
				}
				break;
			default: //filtro non valido 
		}
		return filteredStats;
	}



}
