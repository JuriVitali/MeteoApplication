package util;

public class DateOperations {
	
	public static int getAnno (String time) {
		String a = time.substring(0,4);
		int anno = Integer.parseInt(a);
		return anno;
	}
	
	public static int getMese (String time) {
		String m = time.substring(5,7);
		int mese = Integer.parseInt(m);
		return mese;
	}
	
	public static int getGiorno (String time) {
		String g = time.substring(8);
		int giorno = Integer.parseInt(g);
		return giorno;
	}
}
