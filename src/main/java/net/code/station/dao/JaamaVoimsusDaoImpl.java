package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.code.station.model.AMeetriNait;
import net.code.station.model.JaamaVoimsus;
import net.code.station.model.Offer;

public class JaamaVoimsusDaoImpl implements JaamaVoimsusDAO{
private JdbcTemplate jdbcTemplate;
	
	public JaamaVoimsusDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public List<JaamaVoimsus> list() {
		String sql = "SELECT * FROM jaamavoimsus";		
		RowMapper<JaamaVoimsus> rowMapper = new RowMapper<JaamaVoimsus>() {
			@Override
			public JaamaVoimsus mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("jaamavoimsusid");
				Integer perioodid = rs.getInt("perioodid");
				Time aeg = rs.getTime("aeg");
				Integer pakutav = rs.getInt("pakutav");
				Integer tellitud = rs.getInt("tellitud");
				Integer jaak = rs.getInt("jaak");
				Integer too = rs.getInt("too");
				Double koormusTegur = rs.getDouble("koormustegur");
				return new JaamaVoimsus(id, perioodid, aeg, pakutav,
						tellitud, jaak, too, koormusTegur);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}
	@Override
	public Integer getmaxOffer(Integer perioodid) {
		System.out.println("JaamaVoimsusDaoImpl>getmaxOffer");
		//järg
		String sql = "SELECT MAX(pakutav) AS maxpakutav FROM jaamavoimsus WHERE perioodid=" + perioodid;
		
		ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer maxpakutav = rs.getInt("maxpakutav");
					
					return maxpakutav;
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
		//return -1;
	}
	@Override
	public int update(JaamaVoimsus jV) {
        String sql = "UPDATE jaamavoimsus SET pakutav=?, too=? WHERE jaamavoimsusid=?";
		
		return jdbcTemplate.update(sql, jV.getPakutav(), jV.getToo(), jV.getId());
	}
	@Override
	public List<JaamaVoimsus> listByPeriood(Integer perioodid) {
		String sql = "SELECT * FROM jaamavoimsus WHERE perioodid=" + perioodid;		
		RowMapper<JaamaVoimsus> rowMapper = new RowMapper<JaamaVoimsus>() {
			@Override
			public JaamaVoimsus mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("jaamavoimsusid");
				Integer perioodid = rs.getInt("perioodid");
				Time aeg = rs.getTime("aeg");
				Integer pakutav = rs.getInt("pakutav");
				Integer tellitud = rs.getInt("tellitud");
				Integer jaak = rs.getInt("jaak");
				Integer too = rs.getInt("too");
				Double koormusTegur = rs.getDouble("koormustegur");
				return new JaamaVoimsus(id, perioodid, aeg, pakutav,
						tellitud, jaak, too, koormusTegur);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}
	@Override
	public int save(JaamaVoimsus jV) {
		String sql = "INSERT INTO jaamavoimsus (perioodid, aeg, koormustegur) VALUES (?, ?, ?)";
		return jdbcTemplate.update(
				sql, jV.getPerioodid(), jV.getAeg(), jV.getKoormusTegur());
	}
	
	

}
