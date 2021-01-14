package model;

public class Record extends City{
	private Data date;
	private double temperature;
	private double tempPer;
	private double tempMax;
	private double tempMin;


	public Record() {
		super(0, "");
		this.date = new Data(0,0,0);
		this.temperature = 0;
		this.tempPer = 0;
		this.tempMax = 0;
		this.tempMin = 0;
	}
	
	public Record(long id, String name, Data date, double temperature, double tempPer, double tempMax, double tempMin) {
		super(id, name);
		this.date = date;
		this.temperature = temperature;
		this.tempPer = tempPer;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
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
