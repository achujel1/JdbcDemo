package org.roba.javabrains;

import org.roba.javabrains.dao.JdbcDaoImpl;
import org.roba.javabrains.model.Circle;

public class JdbcDemo {
	public static void main(String[] args) {
		Circle circle = new JdbcDaoImpl().getCircle(1);
		System.out.println(circle.getName());
	}
}
