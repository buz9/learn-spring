/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Author : tungtt Aug 20, 2016
 */
public class StudentJdbcDAO {
	private final static Logger LOGGER = Logger.getLogger(StudentJdbcDAO.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private String insertQuery;
	private String updateAgeByNameSQL = "update age where name = ?";

	@Autowired
	private PlatformTransactionManager transactionManager;

	public void save(Object name, Object age) throws Exception {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		String countQuery = "SELECT COUNT(*) FROM STUDENT";
		try {
			Integer total = jdbcTemplate.queryForObject(countQuery, Integer.class);
			LOGGER.info("before save data, total record is " + total);

			String sql = "Insert into Student(name, age) values (?,?)";
			jdbcTemplate.update(sql, name, age);

			total = jdbcTemplate.queryForObject(countQuery, Integer.class);
			LOGGER.info("after save data, total record is " + total);

			String countQuery2 = "SELECT COUNT(*) FROM STUDENT";
			total = jdbcTemplate.queryForObject(countQuery2, Integer.class);

			transactionManager.commit(status);
		} catch (Exception e) {
			LOGGER.error(e, e);
			transactionManager.rollback(status);

			Integer total = jdbcTemplate.queryForObject(countQuery, Integer.class);
			LOGGER.info("after rollback, total record is " + total);
			throw new Exception(e.getMessage());
		}
	}

	public void setInsertQuery(String insertQuery) {
		this.insertQuery = insertQuery;
	}

	public void setUpdateAgeByNameSQL(String updateAgeByNameSQL) {
		this.updateAgeByNameSQL = updateAgeByNameSQL;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(String name, int age) {
		jdbcTemplate.update(insertQuery, name, age);
		LOGGER.info("Created Record Name = " + name + " Age = " + age);
	}

	public void updateAgeByName(String name, int age) {
		jdbcTemplate.update(updateAgeByNameSQL, age, name);
		LOGGER.info("Update Record Name = " + name + " Age = " + age);
	}

	public int totalRecord() {
		// return jdbcTemplate.execute(new StatementCallback<Integer>() {
		// @Override
		// public Integer doInStatement(Statement statement) throws
		// SQLException, DataAccessException {
		// ResultSet rs = statement.executeQuery("select count(*) from
		// student");
		// return rs.next() ? rs.getInt(1) : 0;
		// }
		// });

		return jdbcTemplate.execute((Statement statement) -> {
			ResultSet rs = statement.executeQuery("select count(*) from student");
			return rs.next() ? rs.getInt(1) : 0;
		});
	}

	private void createTableIfNotExist() throws SQLException {
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
		if (rs.next()) {
			LOGGER.info("Table " + rs.getString("TABLE_NAME") + " already exist!");
			return;
		}

		jdbcTemplate.execute("CREATE TABLE STUDENT ("
				+ " id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ " name VARCHAR(1000)," + " age INTEGER)");

	}

	public class StudentRowMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int i) throws SQLException {
			try {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				return student;
			} catch (Exception e) {
				LOGGER.error(e, e);
				return null;
			}
		}
	}

	public List<Student> loadStudent(String name) {
		return jdbcTemplate.query("SELECT * FROM STUDENT WHERE NAME LIKE '%" + name + "%'", new StudentRowMapper());
	}

	public int[] add(List<Student> students) {
		List<Object[]> batch = new ArrayList<>();
		students.forEach(student -> batch.add(new Object[] { student.getName(), student.getAge() }));
		return jdbcTemplate.batchUpdate(insertQuery, batch);
	}
}
