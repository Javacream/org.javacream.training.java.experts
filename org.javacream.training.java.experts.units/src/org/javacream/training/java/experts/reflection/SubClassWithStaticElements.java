package org.javacream.training.java.experts.reflection;

public class SubClassWithStaticElements extends ClassWithStaticElements{

	public static String description = "a sub class with static elements";
	
	public static String info(){
		//No super available!
		return description;
	}
}
