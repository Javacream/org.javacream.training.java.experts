package org.javacream.training.java.experts.xml.stax;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class StaxUtil {

	public static void print(XMLStreamReader reader) throws Exception{
		System.out.println(reader.getEventType() == XMLEvent.START_DOCUMENT);
		while (reader.hasNext()) {
			reader.next();
			int eventType = reader.getEventType();
			if (eventType == XMLEvent.START_ELEMENT) { 
				System.out.print("> " + reader.getName());
				for (int i = 0; i < reader.getAttributeCount(); i++)
					System.out.print(" " + reader.getAttributeValue(i));
				System.out.println();
			}
			else if (eventType == XMLEvent.CHARACTERS) 
				System.out.println("  " + reader.getText());
			else if (eventType == XMLEvent.END_ELEMENT) 
				System.out.println("< " + reader.getName());
//			else
//				System.out.println(" (other) " + eventType);
		}
		System.out.println(reader.getEventType() == XMLEvent.END_DOCUMENT);

	}
	{
		
	}
}
