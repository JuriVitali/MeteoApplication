package com.MeteoApplication.stats;

import java.util.Vector;
import com.MeteoApplication.model.Record;

/**
 * StatsCalculator e' una classe che rappresenta un calcolatore di statistiche. Dato un Vector di tipo
 * {@link com.MeteoApplication.model.Record  Record} , un oggetto di tale classe e' in grado di 
 * calcolare varie statistiche sulla temperatura.
 * Tutti i metodi di tale classe fanno uso dei metodi dell'interfaccia {@link com.MeteoApplication.stats.BasicStats BasicStats}
 * 
 * @author Roberto Palladino
 *
 */
public class StatsCalculator {
	private BasicStats basic = new BasicStatsImpl();
	private Vector<Record> misurazioni;
	
	/**
	 * Costruttore
	 * 
	 * @param misurazioni indica l'array contenente le misurazioni sulle quali si vogliono eseguire le statistiche
	 */
	public StatsCalculator(Vector<Record> misurazioni) {
		this.misurazioni = misurazioni;
	}
	
	
	/**
	 * Metodo che consente di calcolare la temperatura reale media.
	 * Utilizza il metodo {@link com.MeteoApplication.stats.BasicStats#avg(Vector) avg} dell'interfaccia BasicStats
	 * 
	 * @return la temperatura reale media
	 */
	public double calculateRealTempAvg() { //temperatura reale media
		Vector<Double> temp = new Vector<Double>();
		for (Record misura : misurazioni) temp.add(misura.getTemperature());
		return basic.avg(temp);
	}
	
	
	/**
	 * Metodo che consente di calcolare la temperatura reale minima.
	 * Utilizza il metodo {@link com.MeteoApplication.stats.BasicStats#min(Vector) min} dell'interfaccia BasicStats
	 * 
	 * @return la temperatura reale minima
	 */
	public double calculateRealTempMin() { //temperatura reale minima
		Vector<Double> RealTemp = new Vector<Double>();
		for (Record i : misurazioni) RealTemp.add(i.getTempMin());
		return basic.min(RealTemp);
	}
	
	
	/**
	 * Metodo che consente di calcolare la temperatura reale massima.
	 * Utilizza il metodo {@link com.MeteoApplication.stats.BasicStats#max(Vector) max} dell'interfaccia BasicStats.
	 * 
	 * @return la temperatura reale massima
	 */
	public double calculateRealTempMax() { //temperatura reale massima
		Vector<Double> RealTemp = new Vector<Double>();
		for (Record i : misurazioni) RealTemp.add(i.getTempMax());
		return basic.max(RealTemp);
	}
	
	
	/**
	 * Metodo che consente di calcolare la varianza della temperatura reale.
	 * Utilizza il metodo {@link com.MeteoApplication.stats.BasicStats#variance(Vector) variance} dell'interfaccia BasicStats.
	 * 
	 * @return la varianza della temperatura reale.
	 */
	public double calculateRealTempVariance() { //varianza temperatura reale
		Vector<Double> RealTemp = new Vector<Double>();
		for (Record i : misurazioni) RealTemp.add(i.getTemperature());
		return basic.variance(RealTemp);
	}
	
	
	/**
	 * Metodo che consente di calcolare la temperatura percepita media.
	 * Utilizza il metodo {@link com.MeteoApplication.stats.BasicStats#avg(Vector) avg} dell'interfaccia BasicStats
	 * 
	 * @return la temperatura percepita media
	 */
	public double calculatePercTempAvg() { //temperatura percepita media
		Vector<Double> PercTemp = new Vector<Double>();
		for (Record i : misurazioni) PercTemp.add(i.getTempPer());
		return basic.avg(PercTemp);
	}
	
	
	/**
	 * Metodo che consente di calcolare la temperatura percepita minima.
	 * Utilizza il metodo {@link com.MeteoApplication.stats.BasicStats#min(Vector) min} dell'interfaccia BasicStats
	 * 
	 * @return la temperatura percepita minima.
	 */
	public double calculatePercTempMin() { //temperatura percepita minima
		Vector<Double> PercTemp = new Vector<Double>();
		for (Record i : misurazioni) PercTemp.add(i.getTempPer());
		return basic.min(PercTemp);
	}
	
	
	/**
	 * Metodo che consente di calcolare la temperatura percepita massima.
	 * Utilizza il metodo {@link com.MeteoApplication.stats.BasicStats#max(Vector) max} dell'interfaccia BasicStats.
	 * 
	 * @return la temperatura percepita massima.
	 */
	public double calculatePercTempMax() { //temperatura percepita massima
		Vector<Double> PercTemp = new Vector<Double>();
		for (Record i : misurazioni) PercTemp.add(i.getTempPer());
		return basic.max(PercTemp);
	}
	
	
	/**
	 * Metodo che consente di calcolare la varianza della temperatura percepita.
	 * Utilizza il metodo {@link com.MeteoApplication.stats.BasicStats#variance(Vector) variance} dell'interfaccia BasicStats.
	 * 
	 * @return la varianza della temperatura percepita.
	 */
	public double calculatePercTempVariance() { //varianza temperatura percepita
		Vector<Double> PercTemp = new Vector<Double>();
		for (Record i : misurazioni) PercTemp.add(i.getTempPer());
		return basic.variance(PercTemp);
	}

}
