package model;

public class Record {
	private long id;
	private String name;
	private Data date;
	private double temperature;
	private double tempPer;
	private double tempMax;
	private double tempMin;
	
	public class Data {
		private int giorno;
		private int mese;
		private int anno;
		public Data(int giorno, int mese, int anno) {
			this.giorno = giorno;
			this.mese = mese;
			this.anno = anno;
		}
		public int getGiorno() {
			return giorno;
		}
		public void setGiorno(int giorno) {
			this.giorno = giorno;
		}
		public int getMese() {
			return mese;
		}
		public void setMese(int mese) {
			this.mese = mese;
		}
		public int getAnno() {
			return anno;
		}
		public void setAnno(int anno) {
			this.anno = anno;
		}
		
		
	}

	public Record(long id, String name, Data date, double temperature, double tempPer, double tempMax, double tempMin) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.temperature = temperature;
		this.tempPer = tempPer;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
