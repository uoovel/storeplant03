package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.code.station.model.Contact;
import net.code.station.model.Periood;


public class PerioodDaoImpl implements PerioodDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public PerioodDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Periood> list() {
		String sql = "SELECT * FROM periood";		
		RowMapper<Periood> rowMapper = new RowMapper<Periood>() {
			@Override
			public Periood mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("perioodid");
				Timestamp alates = rs.getTimestamp("alates");
				Timestamp kuni = rs.getTimestamp("kuni");				
				String perNimetus = rs.getString("pernimetus");
				return new Periood(id, alates, kuni, perNimetus);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Periood get(Integer id) {
		String sql = "SELECT * FROM periood WHERE perioodid=" + id;		
		ResultSetExtractor<Periood> extractor = new ResultSetExtractor<Periood>() {
			@Override
			public Periood extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Timestamp alates = rs.getTimestamp("alates");
					Timestamp kuni = rs.getTimestamp("kuni");
					String perNimetus = rs.getString("pernimetus");					
					return new Periood(id, alates, kuni, perNimetus);
				}
				return null;
			}
		};		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public Integer getMaxPakutav(Integer perioodid) {
		String sql = "SELECT maxvoimsus FROM periood WHERE perioodid=" + perioodid;		
		ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer maxvoimsus = rs.getInt("maxvoimsus");
										
					return maxvoimsus;
				}
				return null;
			}
		};		
		return jdbcTemplate.query(sql, extractor);
	}
	
}
