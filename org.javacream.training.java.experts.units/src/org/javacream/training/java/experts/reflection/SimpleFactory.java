package org.javacream.training.java.experts.reflection;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SimpleFactory {

	private Properties properties;
	{
		properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream("simpleFactory.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<?> list() {
		try {
			Class<?> listClass = Class.forName(properties.getProperty("list"));
			List<?> instance = (List<?>) listClass.newInstance();
			return instance;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Set<?> set() {
		try {
			Class<?> setClass = Class.forName(properties.getProperty("set"));
			Set<?> instance = (Set<?>) setClass.newInstance();
			return instance;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	public Map<?, ?> map() {
		try {
			Class<?> mapClass = Class.forName(properties.getProperty("map"));
			Map<?, ?> instance = (Map<?, ?>) mapClass.newInstance();
			return instance;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
