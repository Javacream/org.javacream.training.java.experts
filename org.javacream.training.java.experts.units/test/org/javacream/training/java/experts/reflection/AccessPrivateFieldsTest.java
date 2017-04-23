package org.javacream.training.java.experts.reflection;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

public class AccessPrivateFieldsTest {

	@Test public void accessPrivateField() throws Exception{
		SimpleData data = new SimpleData("From Constructor");
		Assert.assertEquals("From Constructor", data.getData());
		Field dataField = data.getClass().getDeclaredField("data");
		dataField.setAccessible(true);
		dataField.set(data, "Changed");
		Assert.assertEquals("Changed", data.getData());
		
	}
}
