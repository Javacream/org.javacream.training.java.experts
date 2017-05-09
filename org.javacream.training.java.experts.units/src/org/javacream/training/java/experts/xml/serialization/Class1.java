package org.javacream.training.java.experts.xml.serialization;

public class Class1{

	public Class2 getClass2() {
		return class2;
	}

	private String description;
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Class1 [description=" + description + ", class2=" + class2 + "]";
	}

	public void setDescription(String description) {
		this.description = description;
	}

	Class2 class2;

	public void setClass2(Class2 class2) {
		this.class2 = class2;
	}

}
