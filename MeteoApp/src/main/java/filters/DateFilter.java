package filters;

import model.Data;
import util.DateOperations;

public class DateFilter extends Filter{
	
	Parametro param;
	
	public class Parametro{
		String operatore;
		Data date;
		
		public Parametro(String operatore, String date) {
			this.operatore = operatore;
			this.date = new Data(DateOperations.getGiorno(date), DateOperations.getMese(date), DateOperations.getAnno(date));
		}

		public String getOperatore() {
			return operatore;
		}

		public void setOperatore(String operatore) {
			this.operatore = operatore;
		}

		public Data getDate() {
			return date;
		}

		public void setDate(Data date) {
			this.date = date;
		}
	}
	
	

	public DateFilter(String field, Parametro param) {
		setField(field);
		this.param = param;
	}



	@Override
	boolean controlOperator() {
		if (param.getOperatore()=="$gt" || param.getOperatore()=="$gte" || param.getOperatore()=="$bt"|| param.getOperatore()=="$eq") return true;
		return false;
	}
}
