package model;

public class Record extends City{
	private double temperature;
	private double tempPer;
	private double tempMax;
	private double tempMin;
	private String time;
	
	//costruttore
	public Record(long id, String name, double temperature, double tempPer, double tempMax, double tempMin , String time) {
		super(id, name);
		this.temperature = temperature;
		this.tempPer = tempPer;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.time=time;
	}
	
	public Record() {
		super(0, "");
		temperature=0;
		tempPer=0;
		tempMax=0;
		tempMin=0;
		time="";
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Record [id= "+ id + " ,name= " + name +",temperature=" + temperature + ", tempPer=" + tempPer + ", tempMax=" + tempMax + ", tempMin="
				+ tempMin + ", time=" + time + "]";
	}
	
	
}
