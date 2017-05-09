package org.javacream.training.java.experts.annotations;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SimpleJavaDocAnnotation {
	String description();
	String author() default "unknown";
	
}
