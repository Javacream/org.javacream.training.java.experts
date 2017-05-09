package org.javacream.training.java.experts.reflection;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import org.junit.Test;

public class InvokeMethodsTest {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testInstrospection() throws Exception{
		String className = JOptionPane.showInputDialog("Enter class name to load:");
		String methodName = JOptionPane.showInputDialog("Enter method to invoke:");
		Class classToIntrospect = Class.forName(className);
		Object object = classToIntrospect.newInstance();
		Method toInvoke = classToIntrospect.getMethod(methodName);
		System.out.println(toInvoke.invoke(object));
		
	}
}
