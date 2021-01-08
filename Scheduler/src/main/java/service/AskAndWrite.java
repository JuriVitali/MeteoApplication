package service;

import model.*;

import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import org.springframework.web.client.RestTemplate;


public class AskAndWrite {
	private static long id;
	private static String name;
	private static long time;
	private static double temperature;
	private static double tempPer;
	private static double tempMax;
	private static double tempMin;
	
	

	private static String fileName="C:\\Users\\juriv\\git\\RepositoryProgettoEsame\\Scheduler\\src\\main\\java\\service\\CitiesList.txt";
	
	//esegue una chiamata ad OpenWeather per ogni città nella lista
	/*public static void askAndWrite () {
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
				//System.out.println(data);
				JSONObject obj = (JSONObject) JSONValue.parse(data);
				//System.out.println(obj);
				
			    
				//System.out.println(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}*/
	
	//Legge da un file in formato JSON la lista delle città e popola un vettore di City
	//Si può anche far eseguire una volta sola come prima istruzione quando parte lo schedulatore senza esguirlo ogni volta
	private static Vector<City> getCities() {
		Vector<City> ListaCittà = new Vector<City>();
		try {
				JSONParser parser = new JSONParser();
				JSONArray a = (JSONArray) parser.parse(new FileReader (fileName));
				for (Object o : a) {
					JSONObject obj = (JSONObject) o;
					ListaCittà.addElement(new City((long)obj.get("id"),(String)obj.get("name")));
				}
		} catch (IOException | ParseException e) {
			System.out.println("ERRORE");
			e.printStackTrace();
		} 
		return ListaCittà;
	}

	
	public static void parse() {
		Vector<City> lista = getCities();
		for (City c : lista) {
		JSONParser parser = new JSONParser();
		JSONObject obj =null;
		RestTemplate restTemplate = new RestTemplate(); // oggetto che serve per consumare una API
		String result = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id=" + c.getId() + "&units=metric&appid=cb240c9a23197aad47fd81d2660b6b8a", String.class);
		//System.out.println(result);
	    try  {
	    	
	    	String a = "";

            obj = (JSONObject) parser.parse(result);
            id = (Long) obj.get("id");
            time= (Long) obj.get("dt");
            name= (String) obj.get("name");
            JSONObject main = (JSONObject) obj.get("main");
            temperature = Double.parseDouble(main.get("temp").toString());
            tempMin = Double.parseDouble(main.get("temp_min").toString());
            tempMax = Double.parseDouble(main.get("temp_max").toString());
            tempPer = Double.parseDouble(main.get("feels_like").toString());
            
            
            
            
            System.out.println("City [id=" + id + ", name=" + name +", time= " + time + ", temp= " + temperature + ", tempPer= " + tempPer + ", tempmax= " + tempMax + ", tempmin= " + tempMin +"]");
            

        } catch (ParseException e) {
            e.printStackTrace();
        }
	   }
	}
	/*public String toString() {
		return "City [id=" + id + ", name=" + name +", time= " + time + ", temp= " + temperature + ", tempPer= " + tempPer + ", tempmax= " + tempMax + ", tempmin= " + tempMin +"]";
	}	*/
	
	public static void WriteJSONExample()
	{
	    //@SuppressWarnings("unchecked")
	    {
	        // Employee
	        
	        
	    	JSONObject employeeDetails = new JSONObject();
	        employeeDetails.put("Id", id);
	        employeeDetails.put("Name", name);
	        employeeDetails.put("Time", time);
	        employeeDetails.put("Temp", temperature);
	        employeeDetails.put("TempPerc", tempPer);
	        employeeDetails.put("TempMax", tempMax);
	        employeeDetails.put("TempMin", tempMin);
	         
	        JSONObject employeeObject = new JSONObject(); 
	        employeeObject.put("City1", employeeDetails);
	        
	        
	        JSONObject employeeDetails2 = new JSONObject();
	        employeeDetails2.put("Id", id);
	        employeeDetails2.put("Name", name);
	        employeeDetails2.put("Time", time);
	        employeeDetails2.put("temp", temperature);
	        employeeDetails2.put("TempPerc", tempPer);
	        employeeDetails2.put("TempMax", tempMax);
	        employeeDetails2.put("TempMin", tempMin);
	         
	        JSONObject employeeObject2 = new JSONObject(); 
	        employeeObject2.put("City2", employeeDetails2);
	        
	        JSONArray employeeList = new JSONArray();
	        employeeList.add(employeeObject);
	        employeeList.add(employeeObject2);
	         
	         
	        //Add employees to list
	        //JSONArray employeeList = new JSONArray();
	        //employeeList.add(employeeObject);
	       
	         
	        //Write JSON file
	        try (FileWriter file = new FileWriter("C:\\Users\\S3B4\\git\\Progetto-esame-OOP\\Scheduler\\src\\main\\java\\service\\Repository.txt")) {
	        	
	 
	            file.write(employeeList.toJSONString());
	            
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        }
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


