package com.MeteoApplication.database;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.MeteoApplication.model.Metadata;
import com.MeteoApplication.model.Record;
import com.MeteoApplication.model.Data;
import com.MeteoApplication.Exception.InternalException;
import com.MeteoApplication.model.City;
import com.MeteoApplication.util.DateOperations;

/**
 * Classe che implementa l'interfaccia DataManagement
 * 
 * @author Nicola Sebastianelli
 * @author Juri Vitali
 *
 */
public class DataManagementImplementation implements DataManagement {
	
	
	
	/**
	 * {@inheritDoc}
	 */
	public Vector<Metadata> getMetadata() {
		Vector<Metadata> elencoMetadati = new Vector<Metadata>();
		elencoMetadati.add(new Metadata("id","Codice identificativo della città","long"));
		elencoMetadati.add(new Metadata("name","Nome della città","String"));
		elencoMetadati.add(new Metadata("date","Data relativa alla misurazione","Data (aa-mm-gg)"));
		elencoMetadati.add(new Metadata("temperature","Temperatura generale","double"));
		elencoMetadati.add(new Metadata("tempPer","Temperatura percepita","double"));
		elencoMetadati.add(new Metadata("tempMax","Temperatura massima registrata nella città al momento della richiesta","double"));
		elencoMetadati.add(new Metadata("tempMin","Temperatura minima registrata nella città al momento della richiesta","double"));
		elencoMetadati.add(new Metadata("realTempAvg","Temperatura reale media calcolata su un dato periodo","double"));
		elencoMetadati.add(new Metadata("realTempMin","Temperatura reale minima calcolata su un dato periodo","double"));
		elencoMetadati.add(new Metadata("realTempMax","Temperatura reale massima calcolata su un dato periodo","double"));
		elencoMetadati.add(new Metadata("realTempVariance","Varianza della temperatura reale calcolata su un dato periodo","double"));
		elencoMetadati.add(new Metadata("percTempAvg","Temperatura percepita media calcolata su un dato periodo","double"));
		elencoMetadati.add(new Metadata("percTempMin","Temperatura percepita minima calcolata su un dato periodo","double"));
		elencoMetadati.add(new Metadata("percTempMax","Temperatura percepita massima calcolata su un dato periodo","double"));
		elencoMetadati.add(new Metadata("percTempVariance","Varianza della temperatura percepita calcolata su un dato periodo","double"));
		return elencoMetadati;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Vector<Record> getData() {
		return listaDati;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws InternalException 
	 */
	public void downloadAndParseData() {
		String data = "";
		HttpURLConnection openConnection;
		try {
			openConnection = (HttpURLConnection) new URL("https://content.dropboxapi.com/2/files/download").openConnection();
			openConnection.setRequestMethod("POST"); 
			openConnection.setRequestProperty("Authorization", "Bearer shQTHaqinxoAAAAAAAAAAUg4F3FzXp7tj5wWFN8TvnYSzuGBhfgeytEU4CoQ3RTz");
			openConnection.setRequestProperty("Dropbox-API-Arg", "{ \"path\" : \"/"+ nomeFileDati +"\"}");
			InputStream in = openConnection.getInputStream();
			try {
				int next;
				char c;
				InputStreamReader inR = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( inR );
				do {
					next = buf.read();
					c = (char)next;
					data+=c;
				} while( next != ']'); 
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file " + nomeFileDati);
			}
			  finally {
				in.close();
			}
		} catch (IOException e) {
			System.out.println("Errore nella chiamata all'API di Dropbox.");
		}
		Data date = new Data(0,0,0);
		try {
		JSONObject obj =null;
		JSONParser parser = new JSONParser();
		JSONArray a = (JSONArray) parser.parse(data);
		for (Object o : a) {
			        obj=(JSONObject)o;
			        date.setAnno(DateOperations.getAnno((String)obj.get("time")));
					date.setMese(DateOperations.getMese((String)obj.get("time")));
					date.setGiorno(DateOperations.getGiorno((String)obj.get("time")));
					listaDati.addElement(new Record((long) obj.get("id"),(String) obj.get("name"),new Data(date),
							(Double)obj.get("temp"),(Double)obj.get("tempPerc"),(Double)obj.get("tempMax"),(Double)obj.get("tempMin")));
				}
	    } catch ( ParseException e) {
	    	System.out.println("Errore nel parsing dei dati del file " + nomeFileDati);
	    } 
	}
	
	/**
	 * {@inheritDoc}
	 * @throws InternalException 
	 */
	public void downloadAndParseCities() {
		HttpURLConnection openConnection;
		String data = "";
		try {
			openConnection = (HttpURLConnection) new URL("https://content.dropboxapi.com/2/files/download").openConnection();
			openConnection.setRequestMethod("POST");
			openConnection.setRequestProperty("Authorization", "Bearer shQTHaqinxoAAAAAAAAAAUg4F3FzXp7tj5wWFN8TvnYSzuGBhfgeytEU4CoQ3RTz");
			openConnection.setRequestProperty("Dropbox-API-Arg", "{ \"path\": \"/CityList.json\"}");
			InputStream in = openConnection.getInputStream();
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( inR );
			    while ((line = buf.readLine()) != null) {data += line;}
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file " + nomeFileCitta);
			} finally {
				in.close();	
			}
		} catch (IOException e) {
			System.out.println("Errore nella chiamata all'API di Dropbox.");
		} 
		try {
			JSONParser parser = new JSONParser();
			JSONArray a = (JSONArray) parser.parse(data);
			for (Object o : a) {
				JSONObject obj = (JSONObject) o;
				elencoCitta.addElement(new City((long)obj.get("id"),(String)obj.get("name")));
			}
		} catch (ParseException e) {
			System.out.println("Errore nel parsing dei dati del file " + nomeFileCitta);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Vector<City> getCities(String sottostringa) {
		/*prende come parametro una sottostringa, filtra in base ad essa il vector elenco città.
		 *  Infine restituisce un Vector di tipo City il cui nome delle città contiene la sottostringa.
     	*/
		Vector<City> cittaTrovata = new Vector<City>();
		for(City citta : elencoCitta) {
			if ( citta.getName().contains(sottostringa) == true) cittaTrovata.add(new City(citta.getId(),citta.getName()));
		}
		return cittaTrovata;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String takeName(long id) {
		for(City citta : elencoCitta)	if (citta.getId()==id) return citta.getName();
	return null;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws InternalException 
	 */
	public Record getLiveData(long id) throws InternalException {
		//effettua una chiamata all'api di Open-weather per scaricare i dati relativi alla temperatura attuale e li parsa popolando un 
		//elemento di tipo record.Serve un metodo per ottenere la data attuale.
		HttpURLConnection openConnection;
		Record CityTempAtt = new Record();
		JSONObject obj =null;
		//RestTemplate restTemplate = new RestTemplate();
		String result = "";
		JSONParser parser = new JSONParser();
		//result = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id=" + id + "&units=metric&appid=cb240c9a23197aad47fd81d2660b6b8a", String.class);
		try {
			openConnection = (HttpURLConnection) new URL("ttps://api.openweathermap.org/data/2.5/weather?id=" + id + "&units=metric&appid=cb240c9a23197aad47fd81d2660b6b8a").openConnection();
			openConnection.setRequestMethod("GET");
			InputStream in = openConnection.getInputStream();
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( inR );
				while ((line = buf.readLine()) != null) {result += line;}
			} catch (IOException e) {
				throw new InternalException("Errore nella lettura della risposta dell'API di OpenWeather");
			} finally {
				in.close();	
			}
		} catch (IOException e) {
			throw new InternalException("Errore nella chiamata all'API di OpenWeather");
		} 
		try  {
			obj = (JSONObject) parser.parse(result);
			long unixSeconds = (long) obj.get("dt");
			JSONObject main = (JSONObject) obj.get("main");
			CityTempAtt.setId((long) obj.get("id"));
			CityTempAtt.setName((String) obj.get("name"));
			CityTempAtt.setData(DateOperations.ottieniDataAtt(unixSeconds));
			CityTempAtt.setTemperature((Double.parseDouble(main.get("temp").toString())));
		    CityTempAtt.setTempPer((Double.parseDouble(main.get("feels_like").toString())));
			CityTempAtt.setTempMax((Double.parseDouble(main.get("temp_max").toString())));
			CityTempAtt.setTempMin((Double.parseDouble(main.get("temp_min").toString())));
		} catch (ParseException e) {
			throw new InternalException("Errore nel parsing dei dati della risposta");
		}
		return CityTempAtt;
	}	

}