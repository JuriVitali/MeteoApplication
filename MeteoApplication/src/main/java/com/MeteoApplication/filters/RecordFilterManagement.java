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
import com.MeteoApplication.model.Data;
import com.MeteoApplication.model.Record;
import com.MeteoApplication.util.DateOperations;


/**
 * RecordFilterManagement e' una classe che contiene metodi statici per filtrare un vettore di tipo 
 * {@link com.MeteoApplication.model.Record  Record} in base a filtri immessi dall'utente. 
 * 
 * @author Juri Vitali
 * 
 */
public class RecordFilterManagement {
	private static FiltersApplication app = new FiltersApplicationImpl();
	
	/**
	 * parseBody effettua il parsing della parte esterna del filtro andando a determinare la presenza di eventuali
	 * operatori logici
	 * 
	 * @param filtro indica il filtro immesso dall'utente
	 * @param notFilteredArray consiste nell'array da filtrare
	 * @param rotta indica a quale rotta appartiene il metodo che sta chiamando parseBody
	 * @return il vettore filtrato
	 * @throws InvalidFilterException viene lanciata se la sintassi del filtro è errata
	 * @throws IllegalValueException viene lanciata se ci sono valori non coerenti
	 * @throws IllegalOperatorException viene lanciata se ad un campo viene associato un operatore non valido
	 * @throws IllegalFieldException viene lanciata se viene immesso nel filtro un campo non valido
	 */
	public static Vector<Record> parseBody(String filtro, Vector<Record> notFilteredArray, int rotta) 
	  throws InvalidFilterException, IllegalValueException, IllegalOperatorException, IllegalFieldException{
		
		Vector<Record> filteredArray = new Vector<Record>();
		JSONParser parser = new JSONParser();
		JSONObject body;
		try {
			body = (JSONObject) parser.parse(filtro);
		} catch (ParseException e) {
			throw new InvalidFilterException("Filtro non valido: il filtro immesso non rispetta la sintassi di un oggetto JSON");
		}
		
		//verifica se è presente l'operatore logico and
		if(body.containsKey("and")) {
			JSONArray elencoFiltri = (JSONArray) body.get("and"); 
			filteredArray = andMultipleFilterApplicator(elencoFiltri, notFilteredArray, rotta);
		}
		
		//verifica se è presente l'operatore logico or
		else if(body.containsKey("or")) {
			JSONArray elencoFiltri = (JSONArray) body.get("or"); 
			filteredArray = orMultipleFilterApplicator(elencoFiltri, notFilteredArray, rotta);
		}
		
		//se non è presente alcun operatore logico nella parte esterna significa che è presente un solo filtro
			else 
				switch(rotta) {  
					case 4:filteredArray= singleFilterApplicatorRotta4(body,notFilteredArray);break;
					case 5:filteredArray= singleFilterApplicatorRotta5(body,notFilteredArray);break;
					case 6:filteredArray= singleFilterApplicatorRotta6(body,notFilteredArray);break;
				}
		
		//per queste due rotte è sufficiente un record per ogni citta'
		if (rotta==5 || rotta==6) filteredArray = DeleteDuplicatesCities(filteredArray); 
		return filteredArray;
	}
	

	
	// Metodo che gestisce l'applicazione di più filtri legati dall'operatore logico or
	private static Vector<Record> orMultipleFilterApplicator (JSONArray filtri, Vector<Record> notFilteredArray,int rotta) 
	  throws IllegalValueException, IllegalOperatorException, IllegalFieldException{
		
		Vector<Record> finalArray = new Vector<Record>();
		Vector<Record> filteredArray = new Vector<Record>();
		JSONObject filtro = new JSONObject();
		
		//si scorre il JSONArray contenente la lista dei filtri
		for (Object o : filtri) {
			filtro = (JSONObject) o;
			switch(rotta) {
			case 4:filteredArray= singleFilterApplicatorRotta4(filtro,notFilteredArray);break;
			case 5:filteredArray= singleFilterApplicatorRotta5(filtro,notFilteredArray);break;
			case 6:filteredArray= singleFilterApplicatorRotta6(filtro,notFilteredArray);break;
			}
			
			//in questo modo si evita che se un record rispetta più vincoli, esso non venga memorizzato più volte 
			finalArray.removeAll(filteredArray);
			finalArray.addAll(filteredArray);
		}
		return finalArray;
	}
	
	
	
	// Metodo che gestisce l'applicazione di più filtri legati dall'operatore logico and
	private static Vector<Record> andMultipleFilterApplicator (JSONArray filtri, Vector<Record> notFilteredArray,int rotta)
	  throws IllegalValueException, IllegalOperatorException, IllegalFieldException{
		
		Vector<Record> filteredArray = notFilteredArray;
		JSONObject filtro = new JSONObject();
		
		//viene fatto scorrere il JSONArray contenente la lista dei filtri
		for (Object o : filtri) {
			filtro = (JSONObject) o;
			switch(rotta) {
			case 4:filteredArray= singleFilterApplicatorRotta4(filtro,filteredArray);break;
			case 5:filteredArray= singleFilterApplicatorRotta5(filtro,filteredArray);break;
			case 6:filteredArray= singleFilterApplicatorRotta6(filtro,filteredArray);break;
			}
		}
		return filteredArray;
	}

	
	//Metodo che gestisce l'applicazione di un singolo filtro per la rotta "/stats"
	private static Vector<Record> singleFilterApplicatorRotta4 (JSONObject filtro, Vector<Record> notFilteredArray)
			throws IllegalValueException, IllegalOperatorException, IllegalFieldException{
		
		//Controllo del campo
		if (!filtro.containsKey("period")) throw new IllegalFieldException("Campo non valido: questa rotta ammette solo filtri con "
				+ "campo period.");
		String campo = "period",operatore="";
		
		JSONObject interno = (JSONObject)filtro.get(campo);
		@SuppressWarnings("unchecked")
		Iterator<String> iterator = interno.keySet().iterator();
		
		//Ottenimento dell'operatore associato al campo
        operatore = (String) iterator.next();
		Vector<Record> filteredArray = new Vector<Record>();
		
		//selezione del filtro
		switch (operatore) {
			case "$gt":
				
				//Trasforma la stringa in un oggetto di tipo Data
				{String time = (String) interno.get(operatore);
				Data inizio = new Data(DateOperations.getGiorno(time), DateOperations.getMese(time), DateOperations.getAnno(time));
				
				//Applica il filtro
				filteredArray = app.DateFilter_gt(notFilteredArray, inizio);}
				break;
			case "$gte":
				{String time = (String) interno.get(operatore);
				Data inizio = new Data(DateOperations.getGiorno(time), DateOperations.getMese(time), DateOperations.getAnno(time));
				filteredArray = app.DateFilter_gte(notFilteredArray, inizio);}
				break;
			case "$bt":
				
				//Chiama un metodo per prendere entrambe le date contenute nel JSONArray
				Data param[] = getBtDataValues(interno);
				filteredArray = app.DateFilter_bt(notFilteredArray, param[0], param[1]);
				break;
			case "$eq":
				String time = (String) interno.get(operatore);
				Data data = new Data(DateOperations.getGiorno(time), DateOperations.getMese(time), DateOperations.getAnno(time));
				filteredArray = app.DateFilter_eq(notFilteredArray, data);
				break;
			default: throw new IllegalOperatorException("L'operatore " + operatore + " non è valido.", campo); //Se arriva qui significa che il filtro non è valido
		}
		return filteredArray;
	}
	
	
	
	//Metodo che gestisce l'applicazione di un singolo filtro per la rotta "/liveTemp"
	private static Vector<Record> singleFilterApplicatorRotta5 (JSONObject filtro, Vector<Record> notFilteredArray)
			throws IllegalOperatorException, IllegalFieldException{
		
		//controllo del campo
		if (!filtro.containsKey("id")) throw new IllegalFieldException("Campo non valido: questa rotta ammette solo filtri con "
				+ "campo id.");
		String campo = "id";
		JSONObject interno = (JSONObject)filtro.get(campo);
		Vector<Record> filteredArray = new Vector<Record>();
		
		//controllo dell'operatore
		if(interno.containsKey("$eq")) filteredArray = app.idFilter_eq(notFilteredArray, (long) interno.get("$eq"));
		else throw new IllegalOperatorException("Operatore non valido.", campo);
		return filteredArray;
	}
	
	
	
	//Metodo che gestisce l'applicazione di un singolo filtro per la rotta "/cities"
	@SuppressWarnings("unchecked")
	private static Vector<Record> singleFilterApplicatorRotta6 (JSONObject filtro, Vector<Record> notFilteredArray)
	  throws IllegalValueException, IllegalOperatorException, IllegalFieldException{
		
		String campo="";
		Iterator<String> iterator = filtro.keySet().iterator();
		
		//viene ottenuto il campo
        campo = (String) iterator.next();
		String operatore="";
		JSONObject interno = (JSONObject)filtro.get(campo);
		Iterator<String> iterator2 = interno.keySet().iterator();
		
		//viene ottenuto l'operatore
        operatore = (String) iterator2.next();
		Vector<Record> filteredArray = new Vector<Record>();
		
		//Selezione del filtro
		switch (campo) {
		
		case "temperature" : 
			switch(operatore) {
			case "$gt": filteredArray = app.TempFilter_gt(notFilteredArray, (double) interno.get(operatore));break;
			case "$lt": filteredArray = app.TempFilter_less(notFilteredArray, (double) interno.get(operatore));break;
			case "$bt": {double param[] = getBtDoubleValues(interno);
						filteredArray = app.TempFilter_bt(notFilteredArray, param[0], param[1]);
						break;}
			default: throw new IllegalOperatorException("Operatore non valido.", campo); //operatore non valido
			}
			break;
			
		case "tempPer" :
			switch(operatore) {
			case "$gt": filteredArray = app.TempPerFilter_gt(notFilteredArray, (double) interno.get(operatore));break;
			case "$lt": filteredArray = app.TempPerFilter_less(notFilteredArray, (double) interno.get(operatore));break;
			case "$bt": {double param[] = getBtDoubleValues(interno);
						 filteredArray = app.TempPerFilter_bt(notFilteredArray, param[0], param[1]);}
						 break;
			default: throw new IllegalOperatorException("Operatore non valido.", campo); //operatore non valido
			}
			break;
			
		case "period" :
			switch(operatore) {
			case "$gt": {String time = (String) interno.get(operatore);   //viene presa e poi convertita la data nel filtro
			            Data inizio = new Data(DateOperations.getGiorno(time), DateOperations.getMese(time), DateOperations.getAnno(time));
			            filteredArray = app.DateFilter_gt(notFilteredArray, inizio);
			            break;}
			case "$gte":  { String time = (String) interno.get(operatore);
							Data data = new Data(DateOperations.getGiorno(time), DateOperations.getMese(time), DateOperations.getAnno(time));
							System.out.println(data);
							filteredArray = app.DateFilter_gte(notFilteredArray, data);
							break;}
			case "$eq":  { String time = (String) interno.get(operatore);
							Data data = new Data(DateOperations.getGiorno(time), DateOperations.getMese(time), DateOperations.getAnno(time));
							System.out.println(data);
							filteredArray = app.DateFilter_eq(notFilteredArray, data);
							break;}
			case "$bt":  { Data param[] = getBtDataValues(interno);
						   filteredArray = app.DateFilter_bt(notFilteredArray, param[0], param[1]);
						   break; }
			default: throw new IllegalOperatorException("Operatore non valido.", campo); //operatore non valido
			}
			break;
			
		default: throw new IllegalFieldException("Campo non valido: questa rotta ammette solo filtri con "
				+ "campo : temperature, tempPer e period.");   //campo non valido
		}
		return filteredArray;
	}
	
	
	
	//Metodo che consente di prendere i valori double di minimo e massimo associati all'operatore $bt
	static double[] getBtDoubleValues (JSONObject interno) throws IllegalValueException {
		double parametri[] = new double[2];
		JSONArray dati = (JSONArray) interno.get("$bt");
		for(int i=0; i<2; i++) 	parametri[i]=(double) dati.get(i);
		
		//controlla che il seocondo valore sia maggiore o uguale al primo
		if(parametri[0]>parametri[1]) throw new IllegalValueException("In caso di operatore $bt il primo numero non può essere maggiore "
				+ "del secondo");
		return parametri;
	}
	
	
	
	//Metodo che consente di prendere le date passate come estremi associate all'operatore $bt
	static Data[] getBtDataValues (JSONObject interno) throws IllegalValueException {
		Data parametri[] = new Data[2];
		JSONArray dati = (JSONArray) interno.get("$bt");
		for(int i=0; i<2; i++) 	{
			String time = (String) dati.get(i);
			parametri[i]=new Data(DateOperations.getGiorno(time),DateOperations.getMese(time),DateOperations.getAnno(time));
		}
		
		//controlla che la seconda data non sia antecedente alla prima
		if(DateOperations.confrontaDate(parametri[0],parametri[1])==-1) throw new IllegalValueException("In caso di operatore $bt "
				+ " la seconda data non può essere antecedente alla prima.");
		return parametri;
	}
	
	
	
	//Dato un Vector di tipo record questo metodo restituisce un vector che contiene un record per ogni città diversa 
	//nel Vector di partenza
	private static Vector<Record> DeleteDuplicatesCities(Vector<Record> v) {
		Vector<Record> risposta = new Vector<Record>();
		boolean stessaCitta = false;
		for (Record r: v) {
			stessaCitta = false;
			for (Record R : risposta) if(r.getId()==R.getId()) {stessaCitta=true; break;}
			if (stessaCitta==false) risposta.add(new Record(r));}
		return risposta;
	}
	
}
