package org.javacream.training.java.experts.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class JpaNativeTest {

	
	@Test public void testCat(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createNativeQuery("create table MESSAGES (description varchar(1024))").executeUpdate();
		entityManager.createNativeQuery("insert into MESSAGES values('Hello JPA!')").executeUpdate();
		System.out.println(entityManager.createNativeQuery("select * from MESSAGES").getResultList());
		transaction.commit();
		entityManager.close();
	
	}
}
