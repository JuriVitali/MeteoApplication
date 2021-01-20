package com.MeteoApplication.model;

/**
 * La classe Data rappresenta una data 
 * 
 * @author Juri Vitali
 * @author Nicola Sebastianelli
 * @author Roberto Palladino
 * 
 */
public class Data {
	private int giorno;
	private int mese;
	private int anno;
	
	/**
	 * Costruttore  
	 * 
	 * @param giorno indica il giorno
	 * @param mese indica il mese
	 * @param anno indica l'anno
	 */
	public Data(int giorno, int mese, int anno) {
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}
	
	/**
	 * Costruttore copia che inizializza i campi di un oggetto di tipo Data con i valori dei campi di un altro oggetto di tipo data 
	 * 
	 * @param d oggetto di tipo Data di cui si vuole costruire una copia
	 */
	public Data(Data d) {
		this.giorno = d.getGiorno();
		this.mese = d.getMese();
		this.anno = d.getAnno();
	}
	
	public int getGiorno() {
		return giorno;
	}
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}
	public int getMese() {
		return mese;
	}
	public void setMese(int mese) {
		this.mese = mese;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	@Override
	public String toString() {
		return "Data [giorno=" + giorno + ", mese=" + mese + ", anno=" + anno + "]";
	}
	
	
}