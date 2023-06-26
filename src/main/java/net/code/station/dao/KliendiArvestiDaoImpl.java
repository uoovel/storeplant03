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

import net.code.station.model.ArvestiNait;
import net.code.station.model.KliendiArvesti;
import net.code.station.model.Periood;

public class KliendiArvestiDaoImpl implements KliendiArvestiDAO {

	private JdbcTemplate jdbcTemplate;
	
	public KliendiArvestiDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public KliendiArvesti getArvestiId(Integer klientid) {
		String sql = "SELECT * FROM kliendiarvesti WHERE klientid=" + klientid;
		
		ResultSetExtractor<KliendiArvesti> extractor = new ResultSetExtractor<KliendiArvesti>() {

			@Override
			public KliendiArvesti extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("kliendiarvestiid");
					Integer klientid = rs.getInt("klientid");
					Integer arvestiid = rs.getInt("arvestiid");					
					return new KliendiArvesti(id, klientid, arvestiid);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public KliendiArvesti getArvestiByArvestiId(Integer arvestiid) {
		String sql = "SELECT * FROM kliendiarvesti WHERE arvestiid=" + arvestiid;
		
		ResultSetExtractor<KliendiArvesti> extractor = new ResultSetExtractor<KliendiArvesti>() {

			@Override
			public KliendiArvesti extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("kliendiarvestiid");
					Integer klientid = rs.getInt("klientid");
					Integer arvestiid = rs.getInt("arvestiid");					
					return new KliendiArvesti(id, klientid, arvestiid);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public List<KliendiArvesti> getListByKlientId(Integer klientid) {
		String sql = "SELECT * FROM kliendiarvesti WHERE klientid=" + klientid;		
		RowMapper<KliendiArvesti> rowMapper = new RowMapper<KliendiArvesti>() {
			@Override
			public KliendiArvesti mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("kliendiarvestiid");
				Integer klientid = rs.getInt("klientid");
				Integer arvestiid = rs.getInt("arvestiid");
				return new KliendiArvesti(id, klientid, arvestiid);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}
	

}
