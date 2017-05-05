package org.javacream.util.aspects;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.SerializationUtils;

public class SerializerAspect implements InvocationHandler {
	private Object delegate;
	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		try {
			Object result = method.invoke(delegate, SerializationUtils.clone(args));
			return SerializationUtils.clone((Serializable)result);
		} catch (Throwable e) {
			if (e instanceof InvocationTargetException) {
				e = ((InvocationTargetException) e).getTargetException();
			}
			System.out.println("throwing from " + methodName);
			throw SerializationUtils.clone(e);
		}
	}
	public static Object createAspects(Object toDecorate){
		ClassLoader classLoader = SerializerAspect.class.getClassLoader();
		Class<?>[] interfacesToImplement = toDecorate.getClass().getInterfaces();
		SerializerAspect aspect = new SerializerAspect();
		aspect.setDelegate(toDecorate);
		return Proxy.newProxyInstance(classLoader, interfacesToImplement, aspect);
	}
}