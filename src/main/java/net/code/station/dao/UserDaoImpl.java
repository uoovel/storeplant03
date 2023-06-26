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
import net.code.station.model.User;

public class UserDaoImpl implements UserDAO{
	private JdbcTemplate jdbcTemplate;
	
	public UserDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int save(User c) {
		String sql = "INSERT INTO user (uname, email, password, counter) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, c.getUname(), c.getEmail(), c.getPassword(), 0);
	}

	@Override
	public int update(User c) {
		String sql = "UPDATE user SET uname=?, email=?, password=?, counter=? WHERE userid=?";
		
		return jdbcTemplate.update(sql, c.getUname(), c.getEmail(), c.getPassword(), c.getCounter(), c.getId());
	}

	@Override
	public User get(Integer id) {
		String sql = "SELECT * FROM user WHERE userid=" + id;
		
		ResultSetExtractor<User> extractor = new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String uname = rs.getString("uname");
					String email = rs.getString("email");
					String password = rs.getString("password");
					Integer counter = rs.getInt("counter");
					return new User(id, uname, email, password, counter);
				}
				return null;
			}			
		};		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM user WHERE userid=" + id;
		
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<User> list() {
		String sql = "SELECT * FROM user";
		
		RowMapper<User> rowMapper = new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("userid");
				String uname = rs.getString("uname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				Integer counter = rs.getInt("counter");
				return new User(id, uname, email, password, counter);			
			}			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Integer getUserIdByUName(String userName) {
        String sql = "SELECT userid FROM user WHERE uname='" + userName + "'";
		
		ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					
					Integer userid = rs.getInt("userid");
					return userid;
				}
				return null;
			}			
		};		
		return jdbcTemplate.query(sql, extractor);
	}
}
