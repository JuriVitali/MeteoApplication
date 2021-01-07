package service;

import model.*;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class AskAndWrite {

	private static String fileName="C:\\Users\\juriv\\git\\RepositoryProgettoEsame\\Scheduler\\src\\main\\java\\service\\CitiesList.txt";
	
	//esegue una chiamata ad OpenWeather per ogni città nella lista
	public static void askAndWrite () {
		Vector<City> lista = getCities();
		  for (City c : lista) {
			HttpURLConnection openConnection;
			try {
				//openConnection = (HttpURLConnection) new URL("http://api.openweathermap.org/data/2.5/weather?id=3183089&appid=f8666d2b6010dae97907bc42c8b750b5&units=metric&lang=it").openConnection();
				openConnection = (HttpURLConnection) new URL("http://api.openweathermap.org/data/2.5/weather?id="+c.getId()+"&appid=f8666d2b6010dae97907bc42c8b750b5&units=metric&lang=it").openConnection();
				openConnection.setRequestMethod("GET");
				openConnection.setRequestProperty("Accept", "application/json");
				InputStream in = openConnection.getInputStream();
				String data = null;
				String line = null;
				try {
					InputStreamReader inR = new InputStreamReader( in );
					BufferedReader buf = new BufferedReader( inR );
					while ( ( line = buf.readLine() ) != null ) {
						data +=line;
					}
				} finally {
					in.close();
				 }
				System.out.println(data);
				JSONObject obj = (JSONObject) JSONValue.parse(data);
				//System.out.println(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//Legge da un file in formato JSON la lista delle città e popola un vettore di City
	//Si può anche far eseguire una volta sola come prima istruzione quando parte lo schedulatore sennza esguirlo ogni volta
	private static Vector<City> getCities() {
		Vector<City> ListaCittà = new Vector<City>();
		try {
				City città = new City(0,null);
				JSONParser parser = new JSONParser();
				JSONArray a = (JSONArray) parser.parse(new FileReader (fileName));
				/*for (Object o : a) {
					JSONObject obj = (JSONObject) o;
					città.setId((long)obj.get("id"));
					città.setName((String)obj.get("name"));
					ListaCittà.addElement(città);
				}*/
				for (int i=0;i<a.size();i++) {
					JSONObject obj = (JSONObject) a.get(i);
					città.setId((long)obj.get("id"));
					città.setName((String)obj.get("name"));
					ListaCittà.addElement(città);
				}
		} catch (IOException | ParseException e) {
			System.out.println("File non trovato");
			e.printStackTrace();
		} 
		System.out.println(ListaCittà);
		return ListaCittà;
	}
}
