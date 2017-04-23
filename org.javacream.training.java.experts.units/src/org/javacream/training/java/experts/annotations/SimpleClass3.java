package org.javacream.training.java.experts.annotations;

public class SimpleClass3{

	@TracingEnabled
	public void doSomething(){
		//...
	}
	public void doSomethingElse(){
		//...
	}
	@TracingEnabled
	public void setValue(String value){
		//...
	}
	
	@TracingEnabled
	public String getValue(){
		return "";
	}
}
