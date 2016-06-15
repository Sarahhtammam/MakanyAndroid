package com.simpleModels;


public abstract class Element {
	
	public Element(Type type) {
		this.type = type;
	}

	public Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
