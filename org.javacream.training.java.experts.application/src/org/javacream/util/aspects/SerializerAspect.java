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
		try {
			Object result = method.invoke(delegate, SerializationUtils.clone(args));
			return SerializationUtils.clone((Serializable)result);
		} catch (Throwable e) {
			if (e instanceof InvocationTargetException) {
				e = ((InvocationTargetException) e).getTargetException();
			}
			throw SerializationUtils.clone(e);
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> T createAspects(T toDecorate){
		ClassLoader classLoader = SerializerAspect.class.getClassLoader();
		Class<?>[] interfacesToImplement = toDecorate.getClass().getInterfaces();
		SerializerAspect aspect = new SerializerAspect();
		aspect.setDelegate(toDecorate);
		return (T) Proxy.newProxyInstance(classLoader, interfacesToImplement, aspect);
	}
}