package org.javacream.training.java.experts.reflection;

public class ClassWithStaticElements {

	public static String description = "a class with static elements";
	public static String identifier = "ClassWithStaticElements";
	
	public static String info(){
		return identifier + ": " + description;
	}
}
