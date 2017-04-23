package org.javacream.training.java.experts.functional;

import java.util.function.Function;

import org.junit.Test;

public class FunctionalTest {

	private static Function<String, String> func;

	@Test public void basicSyntax(){
		Function<String, String> func = (String message) -> {return "Hello " + message;};
		System.out.println(func.apply("Java"));
	}
	@Test public void syntacticSugar(){
		Function<String, String> func = (message) -> {return "Hello " + message;};
		System.out.println(func.apply("Java"));
		Function<String, String> func2 = (message) -> "Hello " + message;
		System.out.println(func2.apply("Java"));
		Function<String, String> func3 = message -> "Hello " + message;
		System.out.println(func3.apply("Java"));
	}

	@Test public void targetTyping(){
		doSomethingWithInteger(p -> p + 1);
		doSomethingWithString(p -> p + 1);
	}
	
	private void doSomethingWithInteger(Function<Integer, Integer> callback){
		System.out.println(callback.apply(42));
	}
	private void doSomethingWithString(Function<String, String> callback){
		System.out.println(callback.apply("42"));
	}


	@Test public void closures(){
		int increment = 2;
		Integer[] values = {5, 6};
		storeLambda(p -> p +  values[1] + increment);
		System.out.println(func.apply("47"));
		//increment = 3; //compiler error: Local variable increment defined in an enclosing scope must be final or effectively final
		values[1] = -11;
		System.out.println(func.apply("47"));
	}

	private void storeLambda(Function<String, String> callback){
		FunctionalTest.func = callback;
	}
}
