package com.stats;

import java.util.Vector;
import com.model.Record;

public class StatsCalculator {
	private BasicStats basic = new BasicStatsImpl();
	private Vector<Record> misurazioni;

	public StatsCalculator(Vector<Record> misurazioni) {
		this.misurazioni = misurazioni;
	}
	
	public double calculateRealTempAvg() { //temperatura reale media
		Vector<Double> temp = new Vector<Double>();
		for (Record misura : misurazioni) temp.add(misura.getTemperature());
		return basic.avg(temp);
	}
	
	public double calculateRealTempMin() { //temperatura reale minima
		Vector<Double> RealTemp = new Vector<Double>();
		for (Record i : misurazioni) RealTemp.add(i.getTempMin());
		return basic.min(RealTemp);
	}
	
	public double calculateRealTempMax() { //temperatura reale massima
		Vector<Double> RealTemp = new Vector<Double>();
		for (Record i : misurazioni) RealTemp.add(i.getTempMax());
		return basic.max(RealTemp);
	}
	
	public double calculateRealTempVariance() { //varianza temperatura reale
		Vector<Double> RealTemp = new Vector<Double>();
		for (Record i : misurazioni) RealTemp.add(i.getTemperature());
		return basic.variance(RealTemp);
	}
	
	public double calculatePercTempAvg() { //temperatura percepita media
		Vector<Double> PercTemp = new Vector<Double>();
		for (Record i : misurazioni) PercTemp.add(i.getTempPer());
		return basic.avg(PercTemp);
	}
	
	public double calculatePercTempMin() { //temperatura percepita minima
		Vector<Double> PercTemp = new Vector<Double>();
		for (Record i : misurazioni) PercTemp.add(i.getTempPer());
		return basic.min(PercTemp);
	}
	
	public double calculatePercTempMax() { //temperatura percepita massima
		Vector<Double> PercTemp = new Vector<Double>();
		for (Record i : misurazioni) PercTemp.add(i.getTempPer());
		return basic.max(PercTemp);
	}
	
	public double calculatePercTempVariance() { //varianza temperatura percepita
		Vector<Double> PercTemp = new Vector<Double>();
		for (Record i : misurazioni) PercTemp.add(i.getTempPer());
		return basic.variance(PercTemp);
	}

}
