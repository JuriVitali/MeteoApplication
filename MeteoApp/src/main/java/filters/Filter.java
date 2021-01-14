package filters;

abstract public class Filter {
	private String field;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	abstract boolean controlOperator();
}
