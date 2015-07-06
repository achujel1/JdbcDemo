package org.roba.javabrains;

import org.roba.javabrains.dao.JdbcDaoImpl;
import org.roba.javabrains.dao.SimpleJdbcDaoImpl;
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

		// Place for some code

	}

	/**
	 * Method which is using super DAO classes
	 * 
	 * @throws BeansException
	 */
	private static void testingDaoSuperClasses() throws BeansException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		SimpleJdbcDaoImpl dao = context.getBean("simpleJdbcDaoImpl",
				SimpleJdbcDaoImpl.class);
		System.out.println(dao.getCircleCount());
	}

	/**
	 * Method which is working with named parameters
	 * 
	 * @throws BeansException
	 */
	private static void testingNamedParameterJdbcTemplate()
			throws BeansException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		JdbcDaoImpl dao = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		dao.insertCircleNamed(new Circle(3, "Third circle"));
		System.out.println(dao.getAllCircles().size());
	}

	/**
	 * Testing writing to database and execution of queries
	 * 
	 * @throws BeansException
	 */
	private static void testingWriteOperations() throws BeansException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		JdbcDaoImpl dao = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		dao.insertCircle(new Circle(2, "Second circle"));
		System.out.println(dao.getAllCircles().size());
		dao.createTriangleTable();
	}

	/**
	 * Here I'm testing RowMapper in Spring
	 * 
	 * @throws BeansException
	 */
	private static void testingRowMapper() throws BeansException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		JdbcDaoImpl dao = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);

		// Getting name of the given circle object id
		System.out.println(dao.getCircleName(1));

		// Getting the address of given circle object
		System.out.println(dao.getCircleForId(1));

		// Getting the name of given circle object
		System.out.println(dao.getCircleForId(1).getName());

		// Getting the size of all circle objects in table
		System.out.println(dao.getAllCircles().size());
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
		testingRowMapper();
		testingWriteOperations();
		testingNamedParameterJdbcTemplate();
		testingDaoSuperClasses();
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
