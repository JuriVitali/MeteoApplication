package com.example.model;


//classe che contiene i metadati
public class Metadata {
	String alias;
	String sourceField;
	String type;
	
	//costruttore
	public Metadata(String alias, String sourceField, String type) {
		this.alias = alias;
		this.sourceField = sourceField;
		this.type = type;
	}

	//getters e setters
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSourceField() {
		return sourceField;
	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
