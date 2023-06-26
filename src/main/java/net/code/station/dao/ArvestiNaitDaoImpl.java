package net.code.station.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.code.station.model.ArvestiNait;
import net.code.station.model.Pakkumine;
import net.code.station.model.Tellimus;

public class ArvestiNaitDaoImpl implements ArvestiNaitDAO {
	private JdbcTemplate jdbcTemplate;
	
	public ArvestiNaitDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public ArvestiNait get(Integer id) {
		String sql = "SELECT * FROM arvestinait WHERE arvestinaitid=" + id;
		
		ResultSetExtractor<ArvestiNait> extractor = new ResultSetExtractor<ArvestiNait>() {

			@Override
			public ArvestiNait extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer arvestiid = rs.getInt("arvestiid");
					Timestamp aeg = rs.getTimestamp("aeg");
					Integer energia = rs.getInt("energia");
					return new ArvestiNait(id, arvestiid, aeg, energia);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}
	
	@Override
	public List<ArvestiNait> list(Integer arvestiid) {		
		String sql = "SELECT * FROM arvestinait WHERE arvestiid=" + arvestiid;		
		RowMapper<ArvestiNait> rowMapper = new RowMapper<ArvestiNait>() {
			@Override
			public ArvestiNait mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("arvestinaitid");
				Integer arvestiid = rs.getInt("arvestiid");
				Timestamp aeg = rs.getTimestamp("aeg");
				Integer energia = rs.getInt("energia");
				return new ArvestiNait(id, arvestiid, aeg, energia);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<ArvestiNait> listForPeriod(Integer arvestiid, Timestamp algus, Timestamp lopp) {
		//Timestamp algus = '2022-04-01 07:00:00';
		String sql = "SELECT * FROM arvestinait WHERE aeg BETWEEN '" + algus + "' AND '" + lopp + "' AND arvestiid=" + arvestiid;		
		RowMapper<ArvestiNait> rowMapper = new RowMapper<ArvestiNait>() {
			@Override
			public ArvestiNait mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("arvestinaitid");
				Integer arvestiid = rs.getInt("arvestiid");
				Timestamp aeg = rs.getTimestamp("aeg");
				Integer energia = rs.getInt("energia");
				return new ArvestiNait(id, arvestiid, aeg, energia);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}
	
}
