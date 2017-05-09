package org.javacream.training.java.experts.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Omnipotent implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();
		switch (returnType.getName()) {
		case "int":
			return 42;
		case "double":
			return 47.11;
		case "java.lang.String":
			return "Hugo";
		case "boolean":
			return true;
		case "void":
			return null;
		default:
			return returnType.newInstance();
		}
	}

	@SuppressWarnings("rawtypes")
	public static Object create(Class... interfaceTypes) {
		ClassLoader classLoader = Omnipotent.class.getClassLoader();
		Omnipotent omnipotent = new Omnipotent();
		return Proxy.newProxyInstance(classLoader, interfaceTypes, omnipotent);
	}
}
