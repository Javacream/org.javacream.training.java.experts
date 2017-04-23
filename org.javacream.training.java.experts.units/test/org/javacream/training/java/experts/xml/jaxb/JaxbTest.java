package org.javacream.training.java.experts.xml.jaxb;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.Assert;
import org.junit.Test;

public class JaxbTest {
	private String xmlFilename = "../publisher.xml";
	private String xsdFilename = "../publisher.xsd";

	@Test
	public void testUnmarshall() throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass().getPackage().getName());
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement<PublisherType> element = unmarshaller
				.unmarshal(new StreamSource(getClass().getResourceAsStream(xmlFilename)), PublisherType.class);
		PublisherType publisherType = element.getValue();
		System.out.println(publisherType);
		Assert.assertEquals(3, publisherType.getBook().size());
		Assert.assertEquals("Springer", publisherType.getName());
	}

	@Test
	public void testMarshall() throws Exception {
		PublisherType publisherType = new PublisherType();
		publisherType.setCity("Heidelberg");
		publisherType.setName("Springer");
		for (int i = 0; i < 2; i++) {
			BookType bookType = new BookType();
			bookType.setIsbn("ISBN" + i);
			bookType.setTitle("Title" + i);
			bookType.setPrice(9.99 + i);
			publisherType.getBook().add(bookType);
		}
		JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass().getPackage().getName());
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(new ObjectFactory().createPublisher(publisherType), System.out);
		System.out.println();
		
	}
	@Test
	public void testValidationOk() throws Exception {
		PublisherType publisherType = new PublisherType();
		publisherType.setCity("Heidelberg");
		publisherType.setName("Springer");
		for (int i = 0; i < 2; i++) {
			BookType bookType = new BookType();
			bookType.setIsbn("ISBN" + i);
			bookType.setTitle("Title" + i);
			bookType.setPrice(9.99 + i);
			publisherType.getBook().add(bookType);
		}
		JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass().getPackage().getName());
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    Schema schema = schemaFactory.newSchema(new StreamSource(getClass().getResourceAsStream(xsdFilename))); 

	    Marshaller marshaller = jaxbContext.createMarshaller();
	    marshaller.setSchema(schema);		
		marshaller.marshal(new ObjectFactory().createPublisher(publisherType), System.out);
		System.out.println();
		
	}
	@Test(expected=MarshalException.class)
	public void testValidationFails() throws Exception {
		PublisherType publisherType = new PublisherType();
		publisherType.setCity("Heidelberg");
		publisherType.setName("Springer");
		for (int i = 0; i < 2; i++) {
			BookType bookType = new BookType();
			bookType.setIsbn("ISBN" + i);
			bookType.setTitle("Title" + i);
			bookType.setPrice(-9.99 + i);
			publisherType.getBook().add(bookType);
		}
		JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass().getPackage().getName());
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    Schema schema = schemaFactory.newSchema(new StreamSource(getClass().getResourceAsStream(xsdFilename))); 

	    Marshaller marshaller = jaxbContext.createMarshaller();
	    marshaller.setSchema(schema);		
		marshaller.marshal(new ObjectFactory().createPublisher(publisherType), System.out);
	}

}
