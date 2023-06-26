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

import net.code.station.model.AMeetriNait;
import net.code.station.model.ArvestiNait;
import net.code.station.model.Periood;

public class AMeetriNaitDaoImpl implements AMeetriNaitDAO{

    private JdbcTemplate jdbcTemplate;
	
	public AMeetriNaitDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<AMeetriNait> list() {
		String sql = "SELECT * FROM ameetrinait";		
		RowMapper<AMeetriNait> rowMapper = new RowMapper<AMeetriNait>() {
			@Override
			public AMeetriNait mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("ameetrinaitid");
				Integer ampermeeterid = rs.getInt("ampermeeterid");
				Timestamp aeg = rs.getTimestamp("aeg");
				Integer vool = rs.getInt("vool");
				return new AMeetriNait(id, ampermeeterid, aeg, vool);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public AMeetriNait getByAjahetk(Timestamp aeg) {
		String sql = "SELECT * FROM ameetrinait WHERE aeg='" + aeg +"'";
		
		ResultSetExtractor<AMeetriNait> extractor = new ResultSetExtractor<AMeetriNait>() {

			@Override
			public AMeetriNait extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("ameetrinaitid");
					Integer ampermeeterid = rs.getInt("ampermeeterid");
					Timestamp aeg = rs.getTimestamp("aeg");
					Integer vool = rs.getInt("vool");
					return new AMeetriNait(id, ampermeeterid, aeg, vool);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	

	@Override
	public Integer getMaxId() {
        String sql = "SELECT MAX(ameetrinaitid) AS max FROM ameetrinait";
		
		ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("max");
					
					return id;
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public AMeetriNait getLast(Integer maxid) {
		String sql = "SELECT * FROM ameetrinait WHERE ameetrinaitid=" + maxid;
		
		ResultSetExtractor<AMeetriNait> extractor = new ResultSetExtractor<AMeetriNait>() {

			@Override
			public AMeetriNait extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer vool = rs.getInt("vool");
					Timestamp aeg = rs.getTimestamp("aeg");			
					return new AMeetriNait(maxid, aeg, vool);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

}
