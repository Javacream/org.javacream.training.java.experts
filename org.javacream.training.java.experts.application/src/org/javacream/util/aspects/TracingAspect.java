package org.javacream.util.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Date;

public class TracingAspect implements InvocationHandler {
	private Object delegate;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		Traced tracedAnnotation = method.getAnnotation(Traced.class);
		boolean traced;
		traced = (tracedAnnotation != null);
		if (! traced){
			Class<?> c = delegate.getClass();
			tracedAnnotation = c.getAnnotation(Traced.class);
			traced = (tracedAnnotation != null);
		}
		String traceMessage = "";
		if (traced) {
			switch (tracedAnnotation.detail()) {
			case LOW: {
				traceMessage = methodName;
				break;
			}
			case MEDIUM: {
				traceMessage = methodName + ": params = " + Arrays.asList(args);
				break;
			}
			case HIGH: {
				traceMessage = methodName + ": params = " + Arrays.asList(args) + " at " + new Date();
				break;
			}
			}
		}
		if (traced) {
			System.out.println("Entering " + traceMessage);

		}
		try {
			Object result = method.invoke(delegate, args);
			if (traced) {
				System.out.println("returning from " + traceMessage);
			}
			return result;
		} catch (Throwable e) {
			if (e instanceof InvocationTargetException) {
				e = ((InvocationTargetException) e).getTargetException();
			}
			if (traced) {
				System.out.println("throwing from " + traceMessage);
			}

			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T createAspects(T toDecorate) {
		ClassLoader classLoader = TracingAspect.class.getClassLoader();
		Class<?>[] interfacesToImplement = toDecorate.getClass().getInterfaces();
		TracingAspect tracingAspect = new TracingAspect();
		tracingAspect.setDelegate(toDecorate);
		return (T) Proxy.newProxyInstance(classLoader, interfacesToImplement, tracingAspect);
	}
}