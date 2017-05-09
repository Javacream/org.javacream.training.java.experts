package org.javacream.training.java.experts.reflection;

import java.util.Arrays;

import javax.swing.JOptionPane;

import org.junit.Test;

public class IntrospectionTest {

	@Test
	public void testInstrospection() throws Exception{
		String className = JOptionPane.showInputDialog("Enter class name to introspect:");
		@SuppressWarnings("rawtypes")
		Class classToIntrospect = Class.forName(className);
		System.out.println(Arrays.asList(classToIntrospect.getMethods()));
		System.out.println(Arrays.asList(classToIntrospect.getConstructors()));
		System.out.println(Arrays.asList(classToIntrospect.getDeclaredFields()));

	}
}
