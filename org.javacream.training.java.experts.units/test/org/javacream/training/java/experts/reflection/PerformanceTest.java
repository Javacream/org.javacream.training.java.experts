package org.javacream.training.java.experts.reflection;

import java.lang.reflect.Method;

import org.junit.Test;

public class PerformanceTest {

	private final int loops = (int)10e8;
	

	@Test
	public void testPerformanceNoReflection(){
		String s = "Hugo";
		for (int i = 0; i < loops; i++){
			s.toString();
		}
	}
	@Test
	public void testPerformanceWithReflection() throws Exception{
		String s = "Hugo";
		Method toString = String.class.getMethod("toString");
		
		for (int i = 0; i < loops; i++){
			toString.invoke(s);
		}
	}
}
