package service;

import model.Record;
import model.City;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Classe che implementa l'interfaccia WeatherService
 * 
 * @author Juri Vitali
 * @author Nicola Sebastianelli
 *
 */


public class WeatherServiceImplementation implements WeatherService {
	/*private static long id;
	private static String name;
	private static String time;
	private static double temperature;
	private static double tempPer;
	private static double tempMax;
	private static double tempMin;*/
	
	
/**
 * {@inheritDoc}
 */
@Override
public Vector<City> getCities(String data) {
	Vector<City> ListaCittà = new Vector<City>();
	try {
		JSONParser parser = new JSONParser();
		JSONArray a = (JSONArray) parser.parse(data);
		for (Object o : a) {
			JSONObject obj = (JSONObject) o;
			ListaCittà.addElement(new City((long)obj.get("id"),(String)obj.get("name")));
		}
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return ListaCittà;
}



/**
 * {@inheritDoc}
 */
@Override
public String download(String nomeFile) {
	HttpURLConnection openConnection;
	String data = "";
	try {
		openConnection = (HttpURLConnection) new URL("https://content.dropboxapi.com/2/files/download").openConnection();
		openConnection.setRequestMethod("POST");
		openConnection.setRequestProperty("Authorization", "Bearer shQTHaqinxoAAAAAAAAAAUg4F3FzXp7tj5wWFN8TvnYSzuGBhfgeytEU4CoQ3RTz");
		openConnection.setRequestProperty("Dropbox-API-Arg", "{ \"path\": \""+nomeFile+"\"}");
		//System.out.println("prova");
		InputStream in = openConnection.getInputStream();
		String line = "";
		try {
			InputStreamReader inR = new InputStreamReader( in );
			BufferedReader buf = new BufferedReader( inR );
			while ( ( line = buf.readLine() ) != null ) {
				data +=line;
			}
		} finally {
			in.close();
		}
	} catch (IOException e) {
		System.out.println("ERRORE");
		e.printStackTrace();
	} 
	return data;
}	



/**
 * {@inheritDoc}
 */
@Override
public void update(String newData, String nomeFile) {
	HttpURLConnection openConnection;
	try {
		openConnection = (HttpURLConnection) new URL("https://content.dropboxapi.com/2/files/upload").openConnection();
		openConnection.setRequestMethod("POST");
		openConnection.setRequestProperty("Authorization", "Bearer shQTHaqinxoAAAAAAAAAAUg4F3FzXp7tj5wWFN8TvnYSzuGBhfgeytEU4CoQ3RTz");
		openConnection.setRequestProperty("Dropbox-API-Arg", "{   \"path\": \""+ nomeFile +"\",    \"mode\": \"overwrite\",    \"autorename\": false   }");
		openConnection.setRequestProperty("Content-Type", "application/octet-stream");
		openConnection.setDoOutput(true);
		try (OutputStream os = openConnection.getOutputStream()) {
			byte[] input = newData.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
	} catch (IOException e) {
		System.out.println("ERRORE");
		e.printStackTrace();
	} 
}


/**
 * {@inheritDoc}
 */
@Override
public Vector<Record> parse(Vector<City> lista) {
	//Record r=new Record();
	Vector<Record> listData = new Vector<Record>();
	//JSONParser parser = new JSONParser();
	JSONObject obj =null;
	RestTemplate restTemplate = new RestTemplate(); // oggetto che serve per consumare una API
	String result;
	for (City c : lista) {
		//Record r=new Record();
		JSONParser parser = new JSONParser();
		//JSONObject obj =null;
		//RestTemplate restTemplate = new RestTemplate(); // oggetto che serve per consumare una API
		result = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id=" + c.getId() + "&units=metric&appid=cb240c9a23197aad47fd81d2660b6b8a", String.class);
		try  {
			obj = (JSONObject) parser.parse(result);
			//r.setId((long) obj.get("id"));
			//r.setName((String) obj.get("name"));
            JSONObject main = (JSONObject) obj.get("main");
			//r.setTemperature(Double.parseDouble(main.get("temp").toString())); 
			//r.setTempMin(Double.parseDouble(main.get("temp_min").toString())); 
			//r.setTempMax(Double.parseDouble(main.get("temp_max").toString()));
			//r.setTempPer(Double.parseDouble(main.get("feels_like").toString())
			listData.add(new Record((long) obj.get("id"),(String) obj.get("name"),(Double.parseDouble(main.get("temp").toString())),
					(Double.parseDouble(main.get("feels_like").toString())),(Double.parseDouble(main.get("temp_max").toString())),
							(Double.parseDouble(main.get("temp_min").toString())),(long) obj.get("dt")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	return listData;
}
	
	
/**
 * {@inheritDoc}
 */
@SuppressWarnings("unchecked")
@Override
public String produceString(Record r) {
	     	JSONObject o = new JSONObject();
	        o.put("id", r.getId());
	        o.put("name", r.getName());
	        o.put("time", r.getTime());
	        o.put("temp", r.getTemperature());
	        o.put("tempPerc", r.getTempPer());
	        o.put("tempMax", r.getTempMax());
	        o.put("tempMin", r.getTempMin());
	        return o.toJSONString();
}
	         
	         

public static void ConvertiUnix(long time) {
		long unixSeconds = 1372339860;
		// convert seconds to milliseconds
		Date date = new Date(unixSeconds*1000L); 
		// the format of your date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); 
		// give a timezone reference for formatting (see comment at the bottom)
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-1")); 
		String formattedDate = sdf.format(date);
		
		
	}
	
}


