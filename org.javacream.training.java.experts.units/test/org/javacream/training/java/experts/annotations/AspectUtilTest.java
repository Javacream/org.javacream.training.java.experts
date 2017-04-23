package org.javacream.training.java.experts.annotations;

import org.junit.Test;

public class AspectUtilTest {

	@Test public void testAspectUtil(){
		SimpleInterface simpleClass1 = new SimpleClass1();
		SimpleInterface simpleClass2 = new SimpleClass2();
		simpleClass1 = AspectUtil.createAspects(simpleClass1);
		simpleClass2 = AspectUtil.createAspects(simpleClass2);
		simpleClass1.doSomething();
		simpleClass2.doSomething();
		
		
	}
}
