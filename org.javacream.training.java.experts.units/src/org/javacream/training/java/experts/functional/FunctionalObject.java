package org.javacream.training.java.experts.functional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class FunctionalObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> dynamicProperties;
	private Set<String> types;
	{
		dynamicProperties = new HashMap<>();
		types = new HashSet<>();
	}

	public Object get(String key) {
		return dynamicProperties.get(key);
	}

	public void set(String key, Object value) {
		dynamicProperties.put(key, value);
	}

	public void unset(String key) {
		dynamicProperties.remove(key);
	}

	@SuppressWarnings("unchecked")
	public Object invoke(String key, Object... params) {
		Object f = dynamicProperties.get(key);
		Function<Object[], Object> function = (Function<Object[], Object>) f;
		return function.apply(params);
	}

	public void addType(String type) {
		types.add(type);
	}

	public void removeType(String type) {
		types.remove(type);
	}

	public boolean typeOf(String type) {
		return types.contains(type);
	}

}
