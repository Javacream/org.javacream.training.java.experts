package org.javacream.training.java.experts.reflection.proxy;

import java.util.ArrayList;
import java.util.List;

import org.javacream.training.java.experts.reflection.proxy.Omnipotent;
import org.javacream.training.java.experts.reflection.proxy.TracingAspect;
import org.junit.Test;

public class DynamicProxyTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testDynamicProxy() {
		List<String> names = new ArrayList<>();
		names = (List<String>) TracingAspect.createAspects(names);
		names.add("Hugo");
		names.add("Emil");
		names.add("Egon");
		names.size();

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testOmnipotent() {
		List<String> names = (List<String>) Omnipotent.create(List.class);
		names.add("Hugo");
		names.add("Emil");
		names.add("Egon");
		System.out.println(names.size());

	}
}
