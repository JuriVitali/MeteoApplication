package com.MeteoApplication.filters;

import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.MeteoApplication.Exception.IllegalFieldException;
import com.MeteoApplication.Exception.IllegalOperatorException;
import com.MeteoApplication.Exception.IllegalValueException;
import com.MeteoApplication.Exception.InvalidFilterException;
import com.MeteoApplication.model.City;
import com.MeteoApplication.model.Statistics;

/**
 * La classe StatisticsFilterManagement e' una classe che contiene metodi statici per filtrare un vettore di tipo 
 * {@link com.MeteoApplication.model.Statistics  Statistics} in base a filtri immessi dall'utente e 
 * restituire le citta' che risettano tale filtro. 
 * 
 * @author Juri Vitali
 *
 */
public class StatisticsFilterManagement {
	private static FiltersApplication app = new FiltersApplicationImpl();
	
	
	/**
	 * Il metodo parseBody effettua il parsing della parte esterna del filtro andando a determinare la presenza di eventuali
	 * operatori logici.
	 * 
	 * @param filtro indica il filtro (in formato JSON).
	 * @param notFilteredArray consiste in un Vector di tipo {@link com.MeteoApplication.model.Statistics  Statistics} da filtrare
	 * @return un Vector di tipo {@link com.MeteoApplication.model.City  City} contenente le citta' le cui statistiche rispettano il filtro inserito dall'utente
	 * @throws InvalidFilterException viene lanciata se la sintassi del filtro è errata
	 * @throws IllegalValueException viene lanciata se ci sono valori non coerenti
	 * @throws IllegalOperatorException viene lanciata se ad un campo viene associato un operatore non valido
	 * @throws IllegalFieldException viene lanciata se viene immesso nel filtro un campo non valido
	 */
	public static Vector<City> parseBody(String filtro, Vector<Statistics> notFilteredArray) 
	  throws IllegalFieldException, IllegalOperatorException, IllegalValueException, InvalidFilterException{
		Vector<City> filteredArray = new Vector<City>();
		JSONParser parser = new JSONParser();
		JSONObject body = null;
		try {
			body = (JSONObject) parser.parse(filtro);
		} catch (ParseException e) {
			throw new InvalidFilterException("Filtro non valido: il filtro immesso non rispetta la sintassi di un oggetto JSON");
		}
		
		//verifica se è presente l'operatore logico and
		if(body.containsKey("and")) {
			JSONArray elencoFiltri = (JSONArray) body.get("and"); 
			filteredArray = andMultipleFilterApplicator(elencoFiltri, notFilteredArray);
		}
		
		//verifica se è presente l'operatore logico or
		else if(body.containsKey("or")) {
			JSONArray elencoFiltri = (JSONArray) body.get("or"); 
			filteredArray = orMultipleFilterApplicator(elencoFiltri, notFilteredArray);
		}
		
		//se non è presente alcun operatore logico nella parte esterna significa che è presente un solo filtro
			else {
				Vector<Statistics> filteredStatArray = new Vector<Statistics>();
				filteredStatArray = singleFilterApplicator(body,notFilteredArray);
				
				//Viene costruito un vector di tipo City contenente solo le citta' le cui statistiche rispettano il filtro
				for(Statistics s: filteredStatArray) filteredArray.add(new City(s.getId(),s.getName()));
			}
		return filteredArray;
	}
	
	
	
	// Metodo che gestisce l'applicazione di più filtri legati dall'operatore logico or
	private static Vector<City> orMultipleFilterApplicator (JSONArray filtri, Vector<Statistics> notFilteredArray)
	  throws IllegalFieldException, IllegalOperatorException, IllegalValueException{
		
		Vector<City> finalArray = new Vector<City>();
		Vector<City> filteredCitiesArray = new Vector<City>();
		Vector<Statistics> filteredStatArray = new Vector<Statistics>();
		JSONObject filtro = new JSONObject();
		
		//scorre il JSONArray contenente la lista dei filtri applicandone uno alla volta
		for (Object o : filtri) {
			filtro = (JSONObject) o;
			filteredStatArray = singleFilterApplicator(filtro,notFilteredArray);
			for(Statistics s: filteredStatArray) filteredCitiesArray.add(new City(s.getId(),s.getName()));
			finalArray.removeAll(filteredCitiesArray);
			finalArray.addAll(filteredCitiesArray);
		}
		return finalArray;
	}
	
	
	
	// Metodo che gestisce l'applicazione di più filtri legati dall'operatore logico and
	private static Vector<City> andMultipleFilterApplicator (JSONArray filtri, Vector<Statistics> notFilteredArray)
	  throws IllegalFieldException, IllegalOperatorException, IllegalValueException{
		
		Vector<Statistics> filteredArray = notFilteredArray;
		Vector<City> finalArray = new Vector<City>(); 
		JSONObject filtro = new JSONObject();
		
		//scorre il JSONArray contenente la lista dei filtri applicandone uno alla volta
		for (Object o : filtri) {
			filtro = (JSONObject) o;
			filteredArray = singleFilterApplicator(filtro,filteredArray);  //ogni volta viene passato l'array filtrato in precedenza
		}
		for(Statistics s: filteredArray) finalArray.add(new City(s.getId(),s.getName()));
		return finalArray;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private static Vector<Statistics> singleFilterApplicator (JSONObject filtro, Vector<Statistics> notFilteredArray)
	  throws IllegalFieldException, IllegalOperatorException, IllegalValueException {
		
		String campo="";
		Iterator<String> iterator = filtro.keySet().iterator();
		
		//viene ottenuto il campo
        campo = (String) iterator.next();
		String operatore="";
		JSONObject interno = (JSONObject)filtro.get(campo);
		Iterator<String> iterator2 = interno.keySet().iterator();
		
		//viene ottenuto l'operatore
        operatore = (String) iterator2.next();
		Vector<Statistics> filteredStats = new Vector<Statistics>();
		
		//Selezione del filtro
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
					default: throw new IllegalFieldException("Campo non valido: questa rotta ammette solo filtri con "
							+ "campo : ReTempAvg, ReTempMax, ReTempMin, ReTempVariance, PercTempAvg, "
							+ "PercTempMax, PercTempMin, PercTempVariance.");  //campo non valido
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
					default: throw new IllegalFieldException("Campo non valido: questa rotta ammette solo filtri con "
							+ "campo : ReTempAvg, ReTempMax, ReTempMin, ReTempVariance, PercTempAvg, "
							+ "PercTempMax, PercTempMin, PercTempVariance."); //campo non valido
				} 
				break;
			
			case "$bt":
				double[] param = RecordFilterManagement.getBtDoubleValues (interno);  // prende i valori all'interno del JSONArray
				switch (campo) {
					case "ReTempAvg":filteredStats = app.reTempAvgIncl(notFilteredArray, param[0], param[1]);break;
					case "ReTempMax":filteredStats = app.reTempMaxIncl(notFilteredArray, param[0], param[1]);break;
					case "ReTempMin":filteredStats = app.reTempMinIncl(notFilteredArray, param[0], param[1]);break;
					case "ReTempVariance":filteredStats = app.reTempVarIncl(notFilteredArray, param[0], param[1]);break;
					case "PercTempAvg":filteredStats = app.percTempAvgIncl(notFilteredArray, param[0], param[1]);break;
					case "PercTempMax":filteredStats = app.percTempMaxIncl(notFilteredArray, param[0], param[1]);break;
					case "PercTempMin":filteredStats = app.percTempMinIncl(notFilteredArray, param[0], param[1]);break;
					case "PercTempVariance":filteredStats = app.percTempVarIncl(notFilteredArray, param[0], param[1]);break;
					default: throw new IllegalFieldException("Campo non valido: questa rotta ammette solo filtri con "
							+ "campo : ReTempAvg, ReTempMax, ReTempMin, ReTempVariance, PercTempAvg, "
							+ "PercTempMax, PercTempMin, PercTempVariance."); //campo non valido
				}
				break;
			default: throw new IllegalOperatorException("Operatore non valido.", campo); //operatore non valido
		}
		return filteredStats;
	}



}
