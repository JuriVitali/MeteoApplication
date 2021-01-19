package com.MeteoApplication.model;


public class Data {
	private int giorno;
	private int mese;
	private int anno;
	
	public Data(int giorno, int mese, int anno) {
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}
	
	//costruttore copia
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