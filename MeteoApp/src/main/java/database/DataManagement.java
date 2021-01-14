package database;

import java.util.Vector;


import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import model.City;
import model.Metadata;
import model.Record;
import model.Data;
import util.DateOperations;

public class DataManagement {
	
	public static Vector<Record>  listaDati = new Vector<Record>();
	public static Vector<City>  elencoCitta = new Vector<City>();
	
	public static Vector<Metadata> getMetadata() {
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
		elencoMetadati.add(new Metadata("percTempVar","Varianza della temperatura percepita calcolata su un dato periodo","double"));
		return elencoMetadati;
	}
	
	public static Vector<Record> getData() {
		return listaDati;
	}
	
	
	public static void downloadAndParseData() {
		String data = "";
		Vector<Record> listaDati = new Vector<Record>();
	    Data date = new Data(0,0,0);
		HttpURLConnection openConnection;
		try {
			openConnection = (HttpURLConnection) new URL("https://content.dropboxapi.com/2/files/download").openConnection();
			openConnection.setRequestMethod("POST");
			openConnection.setRequestProperty("Authorization", "Bearer shQTHaqinxoAAAAAAAAAAUg4F3FzXp7tj5wWFN8TvnYSzuGBhfgeytEU4CoQ3RTz");
			openConnection.setRequestProperty("Dropbox-API-Arg", "{ \"path\" : \"/Data.json\"}");
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
				} while( next != -1); 
				
			} finally {
				in.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
		JSONObject obj =null;
		JSONParser parser = new JSONParser();
		JSONArray a = (JSONArray) parser.parse(data);
		for (Object o : a) {
			        obj=(JSONObject)o;
			        date.setAnno(DateOperations.getAnno((String)obj.get("time")));
					date.setMese(DateOperations.getMese((String)obj.get("time")));
					date.setGiorno(DateOperations.getGiorno((String)obj.get("time")));
					listaDati.addElement(new Record((long) obj.get("id"),(String) obj.get("name"),date,
							(Double)obj.get("temp"),(Double)obj.get("tempPerc"),(Double)obj.get("tempMax"),(Double)obj.get("tempMin")));
				}
	    } catch ( ParseException e) {
		e.printStackTrace(); 
	    } 
	}
	
	
	public static void downloadAndParseCities() {
		//effettua una chiamata a Dropbox per scaricare i dati delle città presenti sul file CityList.json e parsa i dati
		//popolando IL vector di city elencoCittà
		Vector<City> elencoCitta = new Vector<City>();
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
			} finally {
				in.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		try {
			JSONParser parser = new JSONParser();
			JSONArray a = (JSONArray) parser.parse(data);
			for (Object o : a) {
				JSONObject obj = (JSONObject) o;
				elencoCitta.addElement(new City((long)obj.get("id"),(String)obj.get("name")));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static Vector<City> getCities(String sottostringa) {
		/*prende come parametro una sottostringa, filtra in base ad essa il vector elenco città.
		 *  Infine restituisce un Vector di tipo City il cui nome delle città contiene la sottostringa.
     	*/
		Vector<City> cittaTrovata = new Vector<City>();
		for(City citta : elencoCitta) {
			if ( citta.getName().contains(sottostringa) == true) cittaTrovata.add(new City(citta.getId(),citta.getName()));
		}
		return cittaTrovata;
	}
	
	public static String takeName(long id) {
		for(City citta : elencoCitta)	if (citta.getId()==id) return citta.getName();
	return null;
	}
	
	public static Record getLiveData(long id) {
		//effettua una chiamata all'api di Open-weather per scaricare i dati relativi alla temperatura attuale e li parsa popolando un 
		//elemento di tipo record.Serve un metodo per ottenere la data attuale.
		Record CityTempAtt = new Record();
		JSONObject obj =null;
		RestTemplate restTemplate = new RestTemplate();
		String result;
		JSONParser parser = new JSONParser();
		result = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id=" + id + "&units=metric&appid=cb240c9a23197aad47fd81d2660b6b8a", String.class);
		try  {
			obj = (JSONObject) parser.parse(result);
			long unixSeconds = (long) obj.get("dt");
			JSONObject main = (JSONObject) obj.get("main");
			CityTempAtt.setId((long) obj.get("id"));
			CityTempAtt.setName((String) obj.get("name"));
			CityTempAtt.setData(ottieniDataAtt(unixSeconds));
			CityTempAtt.setTemperature((Double.parseDouble(main.get("temp").toString())));
		    CityTempAtt.setTempPer((Double.parseDouble(main.get("feels_like").toString())));
			CityTempAtt.setTempMax((Double.parseDouble(main.get("temp_max").toString())));
			CityTempAtt.setTempMin((Double.parseDouble(main.get("temp_min").toString())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return CityTempAtt;
}	


	public static Data ottieniDataAtt(long unixSeconds) {
		Data data = new Data(0,0,0);
		Date date = new Date(unixSeconds*1000L); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); 
		sdf.setTimeZone(TimeZone.getTimeZone("Italy/Rome")); 
		String formattedDate = sdf.format(date);
		data.setAnno(DateOperations.getAnno(formattedDate));
		data.setMese(DateOperations.getMese(formattedDate));
		data.setGiorno(DateOperations.getGiorno(formattedDate));
		return data;
	}
}