package filters;

public class TempFilter extends Filter{
	
	private Parametro param;
	
	public class Parametro{
		String operatore;
		double dato;
		
		public Parametro(String operatore, double dato) {
			this.operatore = operatore;
			this.dato = dato;
		}
		
		public String getOperatore() {
			return operatore;
		}
		public void setOperatore(String operatore) {
			this.operatore = operatore;
		}
		public double getDato() {
			return dato;
		}
		public void setDato(double dato) {
			this.dato = dato;
		}
		
		
	}

	//costruttore
	public TempFilter(String field, Parametro param) {
		setField(field);
		this.param = param;
	}

	
	@Override
	boolean controlOperator() {
		if (param.getOperatore()=="$gt" || param.getOperatore()=="$gte" || param.getOperatore()=="$bt" || param.getOperatore()=="$lt" || param.getOperatore()=="$lte") return true;
		return false;
	}
}
