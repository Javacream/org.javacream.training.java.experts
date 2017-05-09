package org.javacream.training.java.experts.reflection;

import org.junit.Test;

public class StaticElementsNoPolymorphismTest {

	@SuppressWarnings("static-access")
	@Test
	public void staticElementsDontSupportPolymorphism() throws Exception {
		System.out.println(ClassWithStaticElements.info());
		System.out.println(SubClassWithStaticElements.info());
		System.out.println(new ClassWithStaticElements().info());
		System.out.println(new SubClassWithStaticElements().info());

	}

}
