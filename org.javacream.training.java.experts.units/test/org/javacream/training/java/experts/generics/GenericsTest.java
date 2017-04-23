package org.javacream.training.java.experts.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class GenericsTest {

	@Test
	public void testGenerics() {
		List<String> names = new ArrayList<String>();
		names.add("Hugo");
		// names.add(42);//Compiler Error
		String name = names.get(0);
		Assert.assertEquals("Hugo", name);
	}

	@Test
	public void testGenericsDiamond() {
		List<String> names = new ArrayList<>();
		names.add("Hugo");
		// names.add(42);//Compiler Error
		String name = names.get(0);
		Assert.assertEquals("Hugo", name);
	}

	@Test
	public void testInheritance() {
		List<Double> doubles = new ArrayList<>();
		doubles.add(4.2);
		// doubles.add(42); //Compiler Error
		List<Number> numbers = new ArrayList<>();
		numbers.add(4.2);
		numbers.add(42);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testAny() {
		List raw = new ArrayList<>();
		raw.add(4.2);
		raw.add(new Object());
		raw.add(42);
		List<Object> objects = new ArrayList<>();
		objects.add(4.2);
		objects.add(new Object());
		objects.add(42);

		List<?> noType = new ArrayList<>();
		Assert.assertEquals(0, noType.size()); // OK
		// noType.add(new Object());// compiler error, noType cannot use any
		// methods adding elements

	}

	@Test
	public void testSuperWildcard() {
		List<? super Double> numbers = new ArrayList<Object>();
		numbers.add(4.2);
		// numbers.add(new Object()); //compiler error: could be Number or
		// Object
		// books.add(42); //compiler error: Integer is no superclass

	}

	@Test
	public void testExtendWildcard() {
		List<? extends Number> numbers = new ArrayList<>();
		// numbers.add(42); //compiler error: could be Double or Integer
		// numbers.add(4.2); //compiler error: could be Double or Integer
		for (Number n : numbers) {
			System.out.println(n);
		}
	}

	void list(List<? extends Number> p) {
		Number n = p.get(0);
		System.out.println(n);
	}

	@SuppressWarnings("unchecked")
	<T> T generic1(T param) {
		return (T) new Object();
	}

	<T, U> String generic2(T param, U param2) {
		return "Hugo";
	}

	@Test
	public void reflectionAddHasObjectTypeParam() throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		list.getClass().getMethod("add", Object.class); // OK

	}

	@Test
	public void reflectionAddParameterizedType() throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		Type[] genericInterfaces = list.getClass().getGenericInterfaces();
		for (Type t : genericInterfaces) {
			if (t instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) t;
				System.out.println("TypeName: " + parameterizedType.getTypeName() + ", ActualTypeArguments:"
						+ Arrays.asList(parameterizedType.getActualTypeArguments()) + ", RawType: "
						+ parameterizedType.getRawType());
			} else {
				System.out.println("Not generic: " + t);
			}
		}
		Type[] parameterTypes = list.getClass().getMethod("add", Object.class).getGenericParameterTypes();
		for (Type t : parameterTypes) {
			if (t instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) t;
				System.out.println("TypeName: " + parameterizedType.getTypeName() + ", ActualTypeArguments:"
						+ Arrays.asList(parameterizedType.getActualTypeArguments()) + ", RawType: "
						+ parameterizedType.getRawType());
			} else {
				System.out.println("Not generic: " + t);
			}
		}

	}

	@Test(expected = NoSuchMethodException.class)
	public void reflectionAddHasNoStringTypeParam() throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		list.getClass().getMethod("add", String.class); // runtime error: method
														// not found

	}
	
//	@Test public void testAnnotations(){
//		ArrayList<? extends @MyAnnotation> list = new ArrayList<>();
//		list.add(new MyClass());
//		
//	}
}
