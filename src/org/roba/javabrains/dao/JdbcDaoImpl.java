package org.roba.javabrains.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.roba.javabrains.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	/**
	 * Getting the value of circle name
	 * 
	 * @param circleId
	 * @return
	 */
	public String getCircleName(int circleId) {
		String sql = "SELECT NAME FROM CIRCLE WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { circleId },
				String.class);
	}

	/**
	 * Getting circle object from table circle
	 * 
	 * @return
	 */
	public Circle getCircleForId(int circleId) {
		String sql = "SELECT * FROM CIRCLE WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { circleId },
				new CircleMapper());
	}

	/**
	 * Method which is getting the List of all Circle objects in database
	 * 
	 * @return
	 */
	public List<Circle> getAllCircles() {
		String sql = "SELECT * FROM CIRCLE";
		return jdbcTemplate.query(sql, new CircleMapper());
	}

	/**
	 * Method to get the RowMapper values of Circle objects
	 * 
	 * @author Adminas
	 *
	 */
	private static final class CircleMapper implements RowMapper<Circle> {
		public Circle mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("ID"));
			circle.setName(resultSet.getString("NAME"));
			return circle;
		}
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
