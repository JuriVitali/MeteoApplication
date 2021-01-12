package repository;

import java.util.Vector;
import model.Metadata;
import model.Record;

public class DataManagement {
	
	private static Vector<Record>  listaDati = new Vector<Record>();
	
	public static Vector<Metadata> getMetadata() {
		Vector<Metadata> elencoMetadati = new Vector<Metadata>();
		elencoMetadati.add(new Metadata("id","Codice identificativo della città","long"));
		elencoMetadati.add(new Metadata("name","Nome della città","String"));
		elencoMetadati.add(new Metadata("date","Data relativa alla misurazione","Data (aa-mm-gg)"));
		elencoMetadati.add(new Metadata("temperature","Temperatura generale","double"));
		elencoMetadati.add(new Metadata("tempPer","Temperatura percepita","double"));
		return elencoMetadati;
	}
	
	public static Vector<Record> getData() {
		return listaDati;
	}
	
	public static void downloadAndParse() {
		//listadati=
	}
}
