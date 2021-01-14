package filters;

public class IdFilter extends Filter{
	
	private Parametro param;
	
	public class Parametro{
		String operatore;
		long id;
		
		public Parametro(String operatore, long id) {
			this.operatore = operatore;
			this.id = id;
		}
		public String getOperatore() {
			return operatore;
		}
		public void setOperatore(String operatore) {
			this.operatore = operatore;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
	}

	
	//costruttore
	public IdFilter(String field, Parametro param) {
		setField(field);
		this.param = param;
	}

	
	@Override
	boolean controlOperator() {
		if (param.getOperatore()=="$eq") return true;
		return false;
	}

}
