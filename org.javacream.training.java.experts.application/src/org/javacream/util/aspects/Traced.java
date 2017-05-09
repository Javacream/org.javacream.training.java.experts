package org.javacream.util.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( ElementType.METHOD)

public @interface Traced {
	Detail detail() default Detail.MEDIUM;
	
	public enum Detail{
		HIGH, LOW, MEDIUM;
	}

}

