package org.roba.javabrains;

import org.roba.javabrains.dao.JdbcDaoImpl;
import org.roba.javabrains.model.Circle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class to test JDBC with Spring
 * 
 * @author Adminas
 *
 */
public class JdbcDemo {
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * Here I'm testing how Jdbc template is working
	 * 
	 * @throws BeansException
	 */
	private static void testingJdbcTempalte() throws BeansException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		JdbcDaoImpl dao = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		System.out.println(dao.getCircleCount());
	}

	/**
	 * Here I will keep the tested methods
	 * 
	 * @throws BeansException
	 */
	private static void testedMethods() throws BeansException {
		testeingJdbcInSpring();
		testingJdbcTempalte();
	}

	/**
	 * Testing how JDBC is working with Spring
	 * 
	 * @throws BeansException
	 */
	private static void testeingJdbcInSpring() throws BeansException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		JdbcDaoImpl dao = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);

		Circle circle = dao.getCircle(1);
		System.out.println(circle.getName());
	}
}
