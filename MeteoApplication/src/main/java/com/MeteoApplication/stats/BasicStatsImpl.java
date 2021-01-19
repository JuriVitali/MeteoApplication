package com.MeteoApplication.stats;

import java.util.Vector;

/**
 * BasicsStatsImpl è una classe che implementa l'interfaccia BasicStats, dunque contiene metodi per calcolare
 * alcune statistiche riguardanti elementi contenuti in un Vector di tipo Double
 * 
 * @author Juri Vitali
 * @author Roberto Palladino
 * @see com.MeteoApplication.stats.BasicStats
 */
public class BasicStatsImpl implements BasicStats{

	@Override
	public double max(Vector<Double> temperature) {
		double max = -1000;
		for (Double temperatura : temperature) if(temperatura>max) max=temperatura.doubleValue();
		//Arrotonda a due cifre decimali
		return ( (double) Math.round(max*100) ) /100;
	}

	@Override
	public double min(Vector<Double> temperature) {
		Double min = 60.0;
		for (Double temperatura : temperature) if(temperatura<min) min= temperatura.doubleValue();
		//Arrotonda a due cifre decimali
		return ( (double) Math.round(min*100) ) /100 ;
	}

	@Override
	public double avg(Vector<Double> temperature) {
		double somma=0;
		for (Double temperatura : temperature) somma += temperatura.doubleValue();
		//Calcola media e arrotonda a due cifre decimali
		return ( (double) Math.round((somma/temperature.size())*100) ) /100;
	}

	@Override
	public double variance(Vector<Double> temperature) {
		double sum=0;
		for (Double temperatura : temperature) sum += Math.pow((temperatura.doubleValue() - avg(temperature)),2);
		//Calcola varianza e arrotonda a due cifre decimali
		return ( (double) Math.round((sum/temperature.size()) * 100) ) /100;
	}

}
