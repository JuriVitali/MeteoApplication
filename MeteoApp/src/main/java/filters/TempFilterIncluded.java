package filters;

public class TempFilterIncluded extends Filter{
	
	private Parametro param;
	
	public class Parametro{
		String operatore;
		double[] dati;
		
		public Parametro(String operatore, double[] dati) {
			this.operatore = operatore;
			this.dati[0] = dati[0];
			this.dati[1] = dati[1];
		}

		public String getOperatore() {
			return operatore;
		}

		public void setOperatore(String operatore) {
			this.operatore = operatore;
		}

		public double[] getDati() {
			return dati;
		}

		public void setDati(double[] dati) {
			this.dati = dati;
		}

	}

	//costruttore
	public TempFilterIncluded(String field, Parametro param) {
		setField(field);
		this.param = param;
	}

	
	@Override
	boolean controlOperator() {
		if (param.getOperatore()=="$gt" || param.getOperatore()=="$gte" || param.getOperatore()=="$bt" || param.getOperatore()=="$lt" || param.getOperatore()=="$lte") return true;
		return false;
	}
}
