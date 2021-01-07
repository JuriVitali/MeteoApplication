package service;

import model.*;
import java.util.Vector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AskAndWrite {
	
	public static void askAndWrite () {
		
	}
	
	//Legge da un file in formato JSON la lista delle città e popola un vettore di City
	//Si può anche far eseguire una volta sola come prima istruzione quando parte lo schedulatore sennza esguirlo ogni volta
	public static Vector<City> getCities() {
		Scanner scanner = null;
		Vector<City> ListaCittà = new Vector<City>();
		try {
			scanner = new Scanner(new BufferedReader(new FileReader("CitiesList.txt")));
			//BufferedReader reader = new BufferedReader(new FileReader("CitiesList.txt"));
			String stringaJson = null, controllo = null;
			int count=0;
			while(scanner.hasNext()) {
				controllo=scanner.nextLine();
				if (controllo.contains("{")) count=count+1;
				stringaJson+=controllo;
			}
			ObjectMapper obj = new ObjectMapper();
			for (int i=0;i<count;i++) {
				try {
					ListaCittà.add(i, obj.readValue(stringaJson, City.class));
				} catch (JsonProcessingException e) {
					e.printStackTrace();}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File non trovato");
			e.printStackTrace();
		}
		System.out.println(ListaCittà);
		return ListaCittà;
	}
}
