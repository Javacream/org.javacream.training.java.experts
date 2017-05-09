package org.javacream.training.java.experts.xml.serialization;

public class Class2 {
	
	public Class1 getClass1() {
		return class1;
	}

	private String description;
	@Override
	public String toString() {
		return "Class2 [description=" + description + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	Class1 class1;

	public void setClass1(Class1 class1) {
		this.class1 = class1;
	}

}
