package org.javacream.util.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TracingAspect implements InvocationHandler {
	private Object delegate;
	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		System.out.println("Entering " + methodName);
		try {
			Object result = method.invoke(delegate, args);
			System.out.println("returning from " + methodName);
			return result;
		} catch (Throwable e) {
			if (e instanceof InvocationTargetException) {
				e = ((InvocationTargetException) e).getTargetException();
			}
			System.out.println("throwing from " + methodName);
			throw e;
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> T createAspects(T toDecorate){
		ClassLoader classLoader = TracingAspect.class.getClassLoader();
		Class<?>[] interfacesToImplement = toDecorate.getClass().getInterfaces();
		TracingAspect tracingAspect = new TracingAspect();
		tracingAspect.setDelegate(toDecorate);
		return (T) Proxy.newProxyInstance(classLoader, interfacesToImplement, tracingAspect);
	}
}