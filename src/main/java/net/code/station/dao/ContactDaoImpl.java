package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.code.station.model.Contact;

public class ContactDaoImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ContactDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int save(Contact c) {
		String sql = "INSERT INTO contact (firstname, lastname, email, userid) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(
				sql, c.getName(), c.getLastName(), c.getEmail(), c.getUserid());
	}

	@Override
	public int update(Contact c) {
		String sql = "UPDATE contact SET firstname=?, lastname=?, email=?, userid=? WHERE contactid=?";
		
		return jdbcTemplate.update(sql, c.getName(), c.getLastName(), 
				c.getEmail(), c.getUserid(), c.getId());
	}

	@Override
	public Contact get(Integer id) {
		String sql = "SELECT * FROM contact WHERE contactid=" + id;		
		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {
			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String name = rs.getString("firstname");
					String lastName = rs.getString("lastName");
					String email = rs.getString("email");
					Integer userid = rs.getInt("userid");
					return new Contact(id, name, lastName, email, userid);
				}
				return null;
			}
		};		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM contact WHERE contactid=" + id;
		
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Contact> list() {
		String sql = "SELECT * FROM contact";
		
		RowMapper<Contact> rowMapper = new RowMapper<Contact>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("contactid");
				String name = rs.getString("firstname");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				Integer userid = rs.getInt("userid");
				return new Contact(id, name, lastName, email, userid);			
			}			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Contact getByUserId(Integer userId) {
		String sql = "SELECT * FROM contact WHERE userid=" + userId;
		
		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					//jarg
			        Integer id = rs.getInt("contactid");
					String name = rs.getString("firstname");
					String lastName = rs.getString("lastName");
					String email = rs.getString("email");
					return new Contact(id, name, lastName, email, userId);
				}
				return null;
			}
		
			
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

}
