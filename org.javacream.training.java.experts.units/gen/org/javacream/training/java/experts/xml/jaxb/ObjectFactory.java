//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.04.19 um 08:43:15 AM CEST 
//


package org.javacream.training.java.experts.xml.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.javacream.training.java.experts.xml.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Book_QNAME = new QName("http://www.javacream.org/publisher", "book");
    private final static QName _Publisher_QNAME = new QName("http://www.javacream.org/publisher", "publisher");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.javacream.training.java.experts.xml.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookType }
     * 
     */
    public BookType createBookType() {
        return new BookType();
    }

    /**
     * Create an instance of {@link PublisherType }
     * 
     */
    public PublisherType createPublisherType() {
        return new PublisherType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.javacream.org/publisher", name = "book")
    public JAXBElement<BookType> createBook(BookType value) {
        return new JAXBElement<BookType>(_Book_QNAME, BookType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PublisherType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.javacream.org/publisher", name = "publisher")
    public JAXBElement<PublisherType> createPublisher(PublisherType value) {
        return new JAXBElement<PublisherType>(_Publisher_QNAME, PublisherType.class, null, value);
    }

}
