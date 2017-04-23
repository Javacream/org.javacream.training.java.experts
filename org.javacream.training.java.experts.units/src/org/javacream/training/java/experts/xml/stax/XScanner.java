package org.javacream.training.java.experts.xml.stax;

import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class XScanner {
	
	public final XMLStreamReader reader;
	
	public XScanner(XMLStreamReader reader) throws Exception {
		this.reader = reader;
		this.read();
	}
	
	public boolean isStart(String name) {
		if (this.reader.getEventType() != XMLEvent.START_ELEMENT)
			return false;
		if (! name.equals(this.reader.getName().getLocalPart()))
			return false;
		return true;
	}

	public Map<String,String> getAttributes() {
		Map<String,String> map = new HashMap<String,String>();
		for (int i = 0; i < this.reader.getAttributeCount(); i++)
			map.put(this.reader.getAttributeName(i).toString(), this.reader.getAttributeValue(i));
		return map;
	}
	
	public void start(String name) throws Exception {
		if (this.reader.getEventType() != XMLEvent.START_ELEMENT)
			throw new RuntimeException("startElement expected");
		if (! name.equals(this.reader.getName().getLocalPart()))
			throw new RuntimeException("<" + name + "> expected. found: <" + this.reader.getName() + ">");
		this.read();
	}
	
	public void end(String name) throws Exception {
		if (this.reader.getEventType() != XMLEvent.END_ELEMENT)
			throw new RuntimeException("endElement expected");
		if (! name.equals(this.reader.getName().getLocalPart()))
			throw new RuntimeException("</" + name + "> expected. found: </" + this.reader.getName() + ">");
		this.read();
	}

	public String startTextEnd(String name) throws Exception {
		this.start(name);
		if (this.reader.getEventType() != XMLEvent.CHARACTERS)
			throw new RuntimeException("Characters expected");
		String text = this.reader.getText();
		this.read();
		this.end(name);
		return text;
	}

	private int read() throws Exception {
		while (this.reader.hasNext()) {
			int eventType = this.reader.next();
			if (eventType == XMLEvent.START_ELEMENT
					|| eventType == XMLEvent.CHARACTERS
					|| eventType == XMLEvent.END_ELEMENT) 
				return eventType;
		}
		return this.reader.getEventType();
	}
}
