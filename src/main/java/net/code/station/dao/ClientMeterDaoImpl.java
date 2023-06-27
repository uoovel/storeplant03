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


import net.code.station.model.ClientMeter;
import net.code.station.model.Periood;

public class ClientMeterDaoImpl implements ClientMeterDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ClientMeterDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public ClientMeter getMeterId(Integer klientid) {
		String sql = "SELECT * FROM kliendiarvesti WHERE klientid=" + klientid;
		
		ResultSetExtractor<ClientMeter> extractor = new ResultSetExtractor<ClientMeter>() {

			@Override
			public ClientMeter extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("kliendiarvestiid");
					Integer klientid = rs.getInt("klientid");
					Integer arvestiid = rs.getInt("arvestiid");					
					return new ClientMeter(id, klientid, arvestiid);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public ClientMeter getArvestiByArvestiId(Integer arvestiid) {
		String sql = "SELECT * FROM kliendiarvesti WHERE arvestiid=" + arvestiid;
		
		ResultSetExtractor<ClientMeter> extractor = new ResultSetExtractor<ClientMeter>() {

			@Override
			public ClientMeter extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("kliendiarvestiid");
					Integer klientid = rs.getInt("klientid");
					Integer arvestiid = rs.getInt("arvestiid");					
					return new ClientMeter(id, klientid, arvestiid);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public List<ClientMeter> getListByKlientId(Integer klientid) {
		String sql = "SELECT * FROM kliendiarvesti WHERE klientid=" + klientid;		
		RowMapper<ClientMeter> rowMapper = new RowMapper<ClientMeter>() {
			@Override
			public ClientMeter mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("kliendiarvestiid");
				Integer klientid = rs.getInt("klientid");
				Integer arvestiid = rs.getInt("arvestiid");
				return new ClientMeter(id, klientid, arvestiid);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}
	

}
