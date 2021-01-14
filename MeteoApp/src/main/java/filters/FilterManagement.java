package filters;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import model.City;
import model.Record;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FilterManagement {
	
	static Vector<City> parseBody(String filtro, Vector<Record> notFilteredVector, boolean duplicateCities) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> risultato = new ObjectMapper().convertValue(filtro, HashMap.class);
		if(risultato.containsKey("and")) /*chiamo metodo per applicare filtri multipli con l'and*/;
		else if(risultato.containsKey("or")) /*chiamo metodo per applicare filtri multipli con l'or*/;
			else /*chiamo metodo per applicazione di un filtro singolo*/;
		if (duplicateCities==false) /*chiamo un metodo per far sì che venga restituito un record per ogni città*/;
		return null;
	}
	
	/*Vector<Record> orMultipleFilterApplication (Object filtri(array di filtri), vector da filtrare , elemento che indica la rotta)
	 * crea un record filtrato.
	 * Per ogni filtro chiama l'applicatore di filtri singoli (passando il vector da filtrare )che ritorna un Vector di record filtrato 
	 *  Il vector filtrato prende gli elementi del vector che ritorna il filtro se non li conteneva già.
	 * Ritorna il Vector filtrato
	 */
	
	Vector<Record> orMultipleFilterApplication (){
		return null;
	}
	/*Vector<Record> andMultipleFilterApplication (Object filtri(array di filtri), record da parsare , elemento che indica la rotta)
	 * crea un vector di record filtrato.
	 * Il vector filtrato inizialmente prende il record da parsare
	 * Per ogni filtro chiama l'applicatore di filtri singoli(passandogli il vector filtrato, il filtro e la rotta) che ritorna un Vector di record filtrato
	 *  Il vector filtrato prende gli elementi del vector che ritorna il filtro.
	 * Ritorna il Vector filtrato
	 */
	
	/*
	 * Vector<Record> singleFilterApplicatio(riceve Vector da filtrare, filtro e rotta)
	 * 
	 * 
	 */
	
}
