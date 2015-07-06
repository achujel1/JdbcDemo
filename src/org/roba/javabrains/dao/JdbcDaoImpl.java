package org.roba.javabrains.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.roba.javabrains.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * This ia a dao class implementation of jdbc
 * 
 * @author Adminas
 *
 */
@Component
public class JdbcDaoImpl {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * Getting circle values from database
	 * 
	 * @param circleId
	 * @return
	 */
	public Circle getCircle(int circleId) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM circle where id = ?");
			ps.setInt(1, circleId);

			Circle circle = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name"));
			}
			rs.close();
			ps.close();
			return circle;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e2) {

			}
		}
	}

	/**
	 * Getting the count of object in circle table. Using JdbcTemplate
	 * 
	 * @return
	 */
	public int getCircleCount() {
		String sql = "SELECT COUNT(*) from circle";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
