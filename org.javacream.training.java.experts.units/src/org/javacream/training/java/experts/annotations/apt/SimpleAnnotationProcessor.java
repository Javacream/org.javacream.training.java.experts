package org.javacream.training.java.experts.annotations.apt;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("org.javacream.training.java.experts.annotations.TracingEnabled")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SimpleAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> typeElements, RoundEnvironment roundEnvironment) {
		if (typeElements.size() > 0) {
			Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(typeElements.iterator().next());
			Map<Boolean, List<Element>> annotatedMethods = elements.stream()
					.collect(Collectors.partitioningBy(element -> {
						String simpleName = element.getSimpleName().toString();
						return !(simpleName.startsWith("get") || simpleName.startsWith("set"));
					}));
			List<Element> validMethods = annotatedMethods.get(true);
			List<Element> invalidMethods = annotatedMethods.get(false);
			System.out.println("Valid methods: " + validMethods);
			System.out.println("Invalid methods: " + invalidMethods);

		} else {
			System.out.println("No matching annotation found");
		}
		return true;
	}

}
