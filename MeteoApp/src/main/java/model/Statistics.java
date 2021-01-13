package model;

import java.util.Vector;
import stats.StatsCalculator;

public class Statistics extends City{
	private double realTempAvg;
	private double realTempMin;
	private double realTempMax;
	private double realTempVariance;
	private double percTempAvg;
	private double percTempMin;
	private double percTempMax;
	private double percTempVar;
	
	
	public Statistics(long id, String name, Vector<Record> misurazioni) {
		super(id, name);
		StatsCalculator calculator = new StatsCalculator(misurazioni);
		realTempAvg = calculator.calculateRealTempAvg();
		realTempMin = calculator.calculateRealTempMin();
		realTempMax = calculator.calculateRealTempMax();
		realTempVariance = calculator.calculateRealTempVariance();
		percTempAvg = calculator.calculatePercTempAvg();
		percTempMin = calculator.calculatePercTempMin();
		percTempMax = calculator.calculatePercTempMax();
		percTempVar = calculator.calculatePercTempVariance();
	}


	public double getRealTempAvg() {
		return realTempAvg;
	}


	public void setRealTempAvg(double realTempAvg) {
		this.realTempAvg = realTempAvg;
	}


	public double getRealTempMin() {
		return realTempMin;
	}


	public void setRealTempMin(double realTempMin) {
		this.realTempMin = realTempMin;
	}


	public double getRealTempMax() {
		return realTempMax;
	}


	public void setRealTempMax(double realTempMax) {
		this.realTempMax = realTempMax;
	}


	public double getRealTempVariance() {
		return realTempVariance;
	}


	public void setRealTempVariance(double realTempVariance) {
		this.realTempVariance = realTempVariance;
	}


	public double getPercTempAvg() {
		return percTempAvg;
	}


	public void setPercTempAvg(double percTempAvg) {
		this.percTempAvg = percTempAvg;
	}


	public double getPercTempMin() {
		return percTempMin;
	}


	public void setPercTempMin(double percTempMin) {
		this.percTempMin = percTempMin;
	}


	public double getPercTempMax() {
		return percTempMax;
	}


	public void setPercTempMax(double percTempMax) {
		this.percTempMax = percTempMax;
	}


	public double getPercTempVar() {
		return percTempVar;
	}


	public void setPercTempVar(double percTempVar) {
		this.percTempVar = percTempVar;
	}
	
	
	
}
