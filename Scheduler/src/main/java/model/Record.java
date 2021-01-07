package model;

public class Record extends City{
	private double temperature;
	private double tempPer;
	private double tempMax;
	private double tempMin;
	
	//costruttore
	public Record(String id, String name, double temperature, double tempPer, double tempMax, double tempMin) {
		super(id, name);
		this.temperature = temperature;
		this.tempPer = tempPer;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
	}

	//getters e setters
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
