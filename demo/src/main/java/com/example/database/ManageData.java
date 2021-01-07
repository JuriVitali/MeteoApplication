package com.example.database;

import com.example.model.*;
import java.util.Vector;

public class ManageData {
	private static Vector<Metadata> mymetadata = new Vector<Metadata>();
	
	public static Vector<Metadata> getMetadata() {
		mymetadata.add(new Metadata("name","Nome della città","String"));
		mymetadata.add(new Metadata("id","Codice identificativo della città","int"));
		mymetadata.add(new Metadata("temperature","temperatura","double"));
		mymetadata.add(new Metadata("tempPer","Temperatura percepita","double"));
		mymetadata.add(new Metadata("tempMax","Temperatura massima registrata in un dato momento all'interno della città","double"));
		mymetadata.add(new Metadata("tempMin","Temperatura minima registrata in un dato momento all'interno della città","double"));
		return mymetadata;
	}	
}
