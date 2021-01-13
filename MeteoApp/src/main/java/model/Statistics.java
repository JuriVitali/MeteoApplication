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

}
