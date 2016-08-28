/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring.dao.impl;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;

/**
 * Author : tungtt Aug 23, 2016
 */
@Component
public class StudentDAOImpl implements StudentDAO, DisposableBean {
	private final static Logger LOGGER = Logger.getLogger(StudentDAOImpl.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@PostConstruct
	private void createTableIfNotExist() throws SQLException {
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
		if (rs.next()) {
			return;
		}

		jdbcTemplate.execute("CREATE TABLE STUDENT ("
				+ " id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ " name VARCHAR(1000)," + " age INTEGER)");
	}

	@Override
	public List<Student> list(String name) {
		return jdbcTemplate.query("SELECT * FROM STUDENT WHERE NAME LIKE '%" + name + "%'", new StudentRowMapper());
	}
	
	@Override
	public List<Student> list() {
		return jdbcTemplate.query("SELECT * FROM STUDENT", new StudentRowMapper());
	}

	@Override
	public void insert(Student student) {
		jdbcTemplate.update("INSERT INTO STUDENT(name, age) VALUES (?,?)", student.getName(), student.getAge());
		LOGGER.info("Created Recored Name = " + student.getName());
	}

	@Override
	public void destroy() throws Exception {
		DriverManager.getConnection("jdbc:derby:C:/Java/db;shutdown=true");
	}
	
	public class StudentRowMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			try {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setAge(rs.getInt("age"));
				student.setName(rs.getString("name"));
				return student;
			} catch (Exception e) {
				LOGGER.error(e, e);
				return null;
			}
		}
		
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.execute("DELETE FROM STUDENT WHERE ID =" +id);
	}
	
	@Override
	public Student getById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE ID = " + id, new StudentRowMapper());
	}
	
	@Override
	public void update(Student student) {
		jdbcTemplate.update("UPDATE STUDENT SET NAME=? WHERE ID=?", student.getName(), student.getId());
	}
}
