package com.stats;

import java.util.Vector;

public class BasicStatsImpl implements BasicStats{

	@Override
	public double max(Vector<Double> temperature) {
		double max = -1000;
		for (double temperatura : temperature) if(temperatura>max) max=temperatura;
		return max;
	}

	@Override
	public double min(Vector<Double> temperature) {
		double min = 1000;
		for (double temperatura : temperature) if(temperatura<min) min=temperatura;
		return min;
	}

	@Override
	public double avg(Vector<Double> temperature) {
		double somma=0;
		for (double temperatura : temperature) somma += temperatura;
		return (somma/temperature.size());
	}

	@Override
	public double variance(Vector<Double> temperature) {
		double sum=0;
		for (double temperatura : temperature) sum += Math.pow((temperatura - avg(temperature)),2);
		return (sum/temperature.size());
	}

}
