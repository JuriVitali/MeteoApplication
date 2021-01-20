package com.MeteoApplication.model;

/**
 * Classe che descrive una misurazione della temperatura avvenuta in una data citta'.
 * Record e' un'estensione della classe {@link com.MeteoApplication.model.City  City}.
 * 
 * @author Juri Vitali
 * @author Nicola Sebastianelli
 * @author Roberto Palladino
 * @see com.MeteoApplication.model.Data
 */
public class Record extends City{
	private Data date;
	private double temperature;
	private double tempPer;
	private double tempMax;
	private double tempMin;

	//Costruttore senza parametri
	public Record() {
		super(0, "");
		this.date = new Data(0,0,0);
		this.temperature = 0;
		this.tempPer = 0;
		this.tempMax = 0;
		this.tempMin = 0;
	}
	
	/**
	 * Costruttore
	 * 
	 * @param id indica l'id della citta'
	 * @param name indica il nome della citta'
	 * @param date indica la data in cui e' avvenuta la misurazione
	 * @param temperature indica la temperatura reale media registrata
	 * @param tempPer indica la temperatura percepita registrata
	 * @param tempMax indica la temperatura reale registrata nel punto piu' caldo della citta'
	 * @param tempMin indica la temperatura registrata nel punto piu' freddo della citta'
	 */
	public Record(long id, String name, Data date, double temperature, double tempPer, double tempMax, double tempMin) {
		super(id, name);
		this.date = date;
		this.temperature = temperature;
		this.tempPer = tempPer;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
	}
	
	 /**
	  * Costruttore copia che costruisce un oggetto Record identico a quello che viene passato
	  * 
	  * @param r oggetto di tipo -record che si vuole copiare
	  */
	public Record(Record r) {
		super(r.getId(),r.getName());
		this.setData(r.getData());
		this.temperature = r.getTemperature();
		this.tempPer = r.getTempPer();
		this.tempMax = r.getTempMax();
		this.tempMin = r.getTempMin();
	}
	
	public Data getData() {
		return date;
	}

	public void setData(Data data) {
		this.date = data;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getTempPer() {
		return tempPer;
	}

	public void setTempPer(double tempPer) {
		this.tempPer = tempPer;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}
	
	
}
