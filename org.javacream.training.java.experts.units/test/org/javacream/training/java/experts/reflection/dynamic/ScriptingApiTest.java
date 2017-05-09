package org.javacream.training.java.experts.reflection.dynamic;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Test;

public class ScriptingApiTest {

	@Test public void testScripting() throws Exception{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");
		Reader r = new InputStreamReader(this.getClass().getResourceAsStream("script.js"));
		engine.eval(r);
	}
}
