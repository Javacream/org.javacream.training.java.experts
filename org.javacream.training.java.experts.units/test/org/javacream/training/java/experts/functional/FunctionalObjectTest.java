package org.javacream.training.java.experts.functional;

import java.util.Arrays;
import java.util.function.Function;

import org.junit.Test;

public class FunctionalObjectTest {

	@Test
	public void testFunctionalObject() {
		FunctionalObject person = new FunctionalObject();
		person.set("name", "Sawitzki");
		Function<Object[], Object> sayHello = (param) -> {
			return "Hello, my name is " + person.get("name");
		};
		person.set("sayHello", sayHello);
		person.addType("Person");
		System.out.println(person.invoke("sayHello"));
		FunctionalObject person2 = new FunctionalObject();
		person2.set("name", "Mustermann");
		person2.set("sayHello", sayHello);
		person2.set("company", "Javacream");
		Function<Object[], Object> work = (param) -> {
			return "working " + Arrays.asList(param) + " at " + person2.get("company");
		};
		person2.set("work", work);
		person2.set("university", "LMU");
		Function<Object[], Object> study = (param) -> {
			return "studying " + Arrays.asList(param) + " at " + person2.get("university");
		};
		person2.set("study", study);
		person2.addType("Person");
		person2.addType("Student");
		person2.addType("Worker");
		System.out.println(person2.invoke("work", "hard", "honest"));
		System.out.println(person2.invoke("study", "all day long"));
		System.out.println(person2.invoke("sayHello"));

		person2.unset("study");
		person2.removeType("Student");
		try {
			System.out.println(person2.invoke("study", "all day long"));
		} catch (NullPointerException e) {
			// OK
		}
	}

}
