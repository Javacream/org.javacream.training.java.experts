package org.javacream.training.java.experts.reflection;

import org.junit.Test;

public class SimpleFactoryTest {

	@Test public void testClassForNameAndNewInstance(){
		SimpleFactory context = new SimpleFactory();
		System.out.println(context.list().getClass().getName());
		System.out.println(context.set().getClass().getName());
		System.out.println(context.map().getClass().getName());
	}
}
