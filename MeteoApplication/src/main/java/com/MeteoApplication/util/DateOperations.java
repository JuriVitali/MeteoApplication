package com.MeteoApplication.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.MeteoApplication.Exception.InvalidDateFormatException;
import com.MeteoApplication.model.Data;

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
		int anno = 0;
		try {
			anno = Integer.parseInt(a);
		} catch (NumberFormatException e) {
			throw new InvalidDateFormatException("Il formato della data non è valido. Deve rispettare il seguente formato: aaaa-mm-gg.");
		}
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
		int mese;
		try {
			mese = Integer.parseInt(m);
		} catch (NumberFormatException e) {
			throw new InvalidDateFormatException("Il formato della data non è valido. Deve rispettare il seguente formato: aaaa-mm-gg.");
		}
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
		int giorno;
		try {
			giorno = Integer.parseInt(g);
		} catch (NumberFormatException e) {
			throw new InvalidDateFormatException("Il formato della data non è valido. Deve rispettare il seguente formato: aaaa-mm-gg.");
		}
		return giorno;
	}
	
    /**
     * Metodo che confronta due date (due oggetti di tipo {@link com.MeteoApplication.model.Data  Data} e restituisce:
     * -true se sono uguali;
     * -false se sono diverse.
     * 
     * @param data1 prima data che si vuole confrontare (di tipo {@link com.MeteoApplication.model.Data  Data}).
     * @param data2 seconda data che si vuole confrontare (di tipo {@link com.MeteoApplication.model.Data  Data}).
     * @return true se le date sono uguali, false se sono diverse.
     */
    public static boolean DateUguali(Data data1, Data data2) {
    	if(data1.getAnno() == data2.getAnno() && data1.getMese() == data2.getMese() && data1.getGiorno() == data2.getGiorno()) return true;
    	return false;
    }
    
    /**
     * confrontaDate e' un metodo che confronta due date e determina quale delle due viene prima dell'altra (o eventualmente 
     * se esse sono uguali).
     * 
     * @param data1 prima data che si vuole confrontare (di tipo {@link com.MeteoApplication.model.Data  Data}).
     * @param data2 seconda data che si vuole confrontare (di tipo {@link com.MeteoApplication.model.Data  Data}).
     * @return il metodo ritorna: 1 se la prima e' antecedente alla seconda, 0 se sono uguali e -1 se la prima e' successiva alla seconda.
     */
    public static int confrontaDate(Data data1, Data data2) {
    	if(data1.getAnno() < data2.getAnno()) return 1;
    	if(data1.getAnno() > data2.getAnno()) return -1;
    	
    	//Se arriva qui l'anno è lo stesso
    	if(data1.getMese() < data2.getMese()) return 1;
    	if(data1.getMese() > data2.getMese()) return -1;
    	
    	//Se arriva qui anche il mese è lo stesso
    	if(data1.getGiorno() < data2.getGiorno()) return 1;
    	if(data1.getGiorno() > data2.getGiorno()) return -1;
    	
    	//Se il flusso del programma arriva qui significa che sono uguali
    	return 0;
    }
    
    
    /**
     * Il metodo ottieniDataAtt prende come argomento una data in formato unix e la converte in formato "yyyy-MM-dd".
     * Inoltre popola un oggeto di tipo {@link com.MeteoApplication.model.Data  Data} con anno , mese e giorno relativi 
     * alla data che è stata convertita.
     *  
     * @param unixSeconds data in formato unix
     * @return un oggeto di tipo Data
     */
    public static Data ottieniDataAtt(long unixSeconds) {
		Data data = new Data(0,0,0);
		Date date = new Date(unixSeconds*1000L); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); 
		sdf.setTimeZone(TimeZone.getTimeZone("Italy/Rome")); //definisce il fuso orario
		String formattedDate = sdf.format(date);
		data.setAnno(DateOperations.getAnno(formattedDate));
		data.setMese(DateOperations.getMese(formattedDate));
		data.setGiorno(DateOperations.getGiorno(formattedDate));
		return data;
	}
    
}
