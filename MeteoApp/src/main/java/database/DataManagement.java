package database;

import java.util.Vector;

import model.City;
import model.Metadata;
import model.Record;

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
		elencoMetadati.add(new Metadata("tempMax","Temperatura massima reale","double"));
		elencoMetadati.add(new Metadata("tempMin","Temperatura percepita","double"));
		return elencoMetadati;
	}
	
	public static Vector<Record> getData() {
		return listaDati;
	}
	
	public static void downloadAndParseData() {
		//IMPORTANTE: mettere il vettore parsato in listadati
		//---------------------------------------------------
		//da implementare 
	}
	
	public static void downloadAndParseCities() {
		//effettua una chiamata a Dropboox per scaricare i dati delle città presenti sul file CityList.json e parsa i dati
		//popolando IL vector di city elencoCittà
		
	}

	public static Vector<City> getCities() {
		//da implementare
		/*prende come parametro una sottostringa, filtra in base ad essa il vector elenco città.
		 *  Infine restituisce un Vector di tipo City il cui nome delle città contiene la sottostringa.
     	*/
		return null;
	}
	
	public static String takeName(long id) {
		for(City citta : elencoCitta)	if (citta.getId()==id) return citta.getName();
	return null;
	}
	
	public static Record getLiveData(long id) {
		//effettua una chiamata all'api di Open-weather per scaricare i dati relativi alla temperatura attuale e li parsa popolando un 
		//elemento di tipo record.Serve un metodo per ottenere la data attuale.
		return null;
	}
	
}
