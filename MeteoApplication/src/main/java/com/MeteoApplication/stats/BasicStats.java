package com.MeteoApplication.stats;

import java.util.Vector;

/**
 * BasicsStats è un'interfaccia che contiene metodi per il calcolo di statistiche riguardanti un Vector di tipo Double
 * 
 * @author Juri Vitali
 * @author Roberto Palladino
 *
 */
interface BasicStats {
	
	//ritorna il valore massimo di un vettore contenente dati di tipo double
	double max (Vector<Double> temperature);
	
	//ritorna il valore minimo di un vettore contenente dati di tipo double
	double min (Vector<Double> temperature);
	
	//ritorna il valore medio di un vettore contenente dati di tipo double
	double avg (Vector<Double> temperature);
	
	//ritorna la varianza dei dati di un vettore di tipo double
	double variance (Vector<Double> temperature);
}
