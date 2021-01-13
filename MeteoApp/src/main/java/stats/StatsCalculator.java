package stats;

import java.util.Vector;
import model.Record;

public class StatsCalculator {
	private BasicStats basic = new BasicStatsImpl();
	private Vector<Record> misurazioni;

	public StatsCalculator(Vector<Record> misurazioni) {
		this.misurazioni = misurazioni;
	}
	
	public double calculateRealTempAvg() { //temperatura reale media
		//da implementare
		return 0;
	}
	
	public double calculateRealTempMin() { //temperatura reale minima
		//da implementare
		return 0;
	}
	
	public double calculateRealTempMax() { //temperatura reale massima
		//da implementare
		return 0;
	}
	
	public double calculateRealTempVariance() { //varianza temperatura reale
		//da implementare
		return 0;
	}
	
	public double calculatePercTempAvg() { //temperatura percepita media
		//da implementare
		return 0;
	}
	
	public double calculatePercTempMin() { //temperatura percepita minima
		//da implementare
		return 0;
	}
	
	public double calculatePercTempMax() { //temperatura percepita massima
		//da implementare
		return 0;
	}
	
	public double calculatePercTempVariance() { //varianza temperatura percepita
		//da implementare
		return 0;
	}

}
