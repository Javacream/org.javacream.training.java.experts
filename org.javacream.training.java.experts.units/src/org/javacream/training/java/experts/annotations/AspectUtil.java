package org.javacream.training.java.experts.annotations;

import java.lang.reflect.Proxy;

import org.javacream.training.java.experts.reflection.proxy.TracingAspect;

public class AspectUtil {

	@SuppressWarnings("unchecked")
	public static <T> T createAspects(T toDecorate) {
		if (toDecorate.getClass().getAnnotation(Traced.class) != null) {
			ClassLoader classLoader = TracingAspect.class.getClassLoader();
			Class<?>[] interfacesToImplement = toDecorate.getClass().getInterfaces();
			TracingAspect tracingAspect = new TracingAspect();
			tracingAspect.setDelegate(toDecorate);
			return (T) Proxy.newProxyInstance(classLoader, interfacesToImplement, tracingAspect);
		} else {
			return toDecorate;
		}
	}

}
