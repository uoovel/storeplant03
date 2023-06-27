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

import net.code.station.model.MeterState;

import net.code.station.model.ClientOrder;

public class MeterStateDaoImpl implements MeterStateDAO {
	private JdbcTemplate jdbcTemplate;
	
	public MeterStateDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public MeterState get(Integer id) {
		String sql = "SELECT * FROM arvestinait WHERE arvestinaitid=" + id;
		
		ResultSetExtractor<MeterState> extractor = new ResultSetExtractor<MeterState>() {

			@Override
			public MeterState extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer arvestiid = rs.getInt("arvestiid");
					Timestamp aeg = rs.getTimestamp("aeg");
					Integer energia = rs.getInt("energia");
					return new MeterState(id, arvestiid, aeg, energia);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}
	
	@Override
	public List<MeterState> list(Integer arvestiid) {		
		String sql = "SELECT * FROM arvestinait WHERE arvestiid=" + arvestiid;		
		RowMapper<MeterState> rowMapper = new RowMapper<MeterState>() {
			@Override
			public MeterState mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("arvestinaitid");
				Integer arvestiid = rs.getInt("arvestiid");
				Timestamp aeg = rs.getTimestamp("aeg");
				Integer energia = rs.getInt("energia");
				return new MeterState(id, arvestiid, aeg, energia);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<MeterState> listForPeriod(Integer arvestiid, Timestamp algus, Timestamp lopp) {
		//Timestamp algus = '2022-04-01 07:00:00';
		String sql = "SELECT * FROM arvestinait WHERE aeg BETWEEN '" + algus + "' AND '" + lopp + "' AND arvestiid=" + arvestiid;		
		RowMapper<MeterState> rowMapper = new RowMapper<MeterState>() {
			@Override
			public MeterState mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("arvestinaitid");
				Integer arvestiid = rs.getInt("arvestiid");
				Timestamp aeg = rs.getTimestamp("aeg");
				Integer energia = rs.getInt("energia");
				return new MeterState(id, arvestiid, aeg, energia);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}
	
}
