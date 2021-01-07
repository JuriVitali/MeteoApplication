package com.example.model;

public class Record {
	private String name;
	private int id;
	private double temperature;
	private double tempPer;
	private double tempMax;
	private double tempMin;
	
	//costruttore
	public Record(String name, int id, double temperature, double tempPer, double tempMax, double tempMin) {
		super();
		this.name = name;
		this.id = id;
		this.temperature = temperature;
		this.tempPer = tempPer;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
	}
	
	//getters e setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
