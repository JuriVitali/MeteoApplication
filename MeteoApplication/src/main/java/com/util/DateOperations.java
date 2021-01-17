package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.model.Data;

/**
 * Classe che contiene metodi statici per facilitare la manipolazione di dati che rappresentano 
 * delle date
 * 
 * @author Nicola Sebastianelli
 *
 */
public class DateOperations {
	
	/**
	 * Il metodo getAnno prende come argomento una stringa contente la data in formato "yyyy-MM-dd" e
	 * restituisce l'intero relativo all'anno 
	 * 
	 * @param time stringa contente la data in formato "yyyy-MM-dd"
	 * @return un intero contenente l'anno
	 */
	public static int getAnno (String time) {
		String a = time.substring(0,4);
		int anno = Integer.parseInt(a);
		return anno;
	}
	
	/**
	 * Il metodo getMese prende come argomento una stringa contente la data in formato "yyyy-MM-dd" e
	 * restituisce l'intero relativo al mese 
	 * 
	 * @param time stringa contente la data in formato "yyyy-MM-dd"
	 * @return un intero contenente al mese
	 */
	public static int getMese (String time) {
		String m = time.substring(5,7);
		int mese = Integer.parseInt(m);
		return mese;
	}
	
	/**
	 * Il metodo getGiorno prende come argomento una stringa contente la data in formato "yyyy-MM-dd" e
	 * restituisce l'intero relativo al giorno
	 * 
	 * @param time stringa contente la data in formato "yyyy-MM-dd"
	 * @return un intero contenente il giorno
	 */
	public static int getGiorno (String time) {
		String g = time.substring(8,10);
		int giorno = Integer.parseInt(g);
		return giorno;
	}
	
    public static int confrontaDate(Data data1, Data data2) {
    	if(DateUguali(data1,data2)) return 0;
    	else return confrDateMaggMin(data1,data2);
    		
    }

    public static boolean DateUguali(Data data1, Data data2) {
    	if(data1.getAnno() == data2.getAnno() && data1.getMese() == data2.getMese() && data1.getGiorno() == data2.getGiorno()) return true;
    	return false;
    }

    private static int confrDateMaggMin(Data data1, Data data2) {
    	if(data1.getAnno() < data2.getAnno()) return 1;
    	if(data1.getAnno() > data2.getAnno()) return -1;
    	if(data1.getMese() < data2.getMese()) return 1;
    	if(data1.getMese() > data2.getMese()) return -1;
    	if(data1.getGiorno() < data2.getGiorno()) return 1;
    	if(data1.getGiorno() > data2.getGiorno()) return -1;
    	return 0;
    }
    
    
    /**
     * Il metodo ottieniDataAtt prende come argomento una data in formato unix e la converte in formato "yyyy-MM-dd".
     * Inoltre popola un oggeto di tipo Data con anno , mese e giorno relativi alla data che è stata convertita.
     *  
     * @param unixSeconds data in formato unix
     * @return un oggeto di tipo Data
     */
    public static Data ottieniDataAtt(long unixSeconds) {
		Data data = new Data(0,0,0);
		Date date = new Date(unixSeconds*1000L); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); 
		sdf.setTimeZone(TimeZone.getTimeZone("Italy/Rome")); 
		String formattedDate = sdf.format(date);
		data.setAnno(DateOperations.getAnno(formattedDate));
		data.setMese(DateOperations.getMese(formattedDate));
		data.setGiorno(DateOperations.getGiorno(formattedDate));
		return data;
	}
    
}
