package net.code.station.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.code.station.model.Pakkumine;

public class PakkumineDaoImpl implements PakkumineDAO{
	private JdbcTemplate jdbcTemplate;
	
	public PakkumineDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int save(Pakkumine c) {
		String sql = "INSERT INTO pakkumine (nimetus, hind, kirjeldus, perioodid) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, c.getNimetus(), c.getHind(), c.getKirjeldus(), c.getPerioodid());
	}

	@Override
	public int update(Pakkumine c) {
		String sql = "UPDATE pakkumine SET nimetus=?, hind=?, kirjeldus=?, perioodid=? WHERE pakkumineid=?";
		
		return jdbcTemplate.update(sql, c.getNimetus(), c.getHind(), c.getKirjeldus(), c.getPerioodid(), c.getId());
	}

	@Override
	public Pakkumine get(Integer id) {
		String sql = "SELECT * FROM pakkumine WHERE pakkumineid=" + id;
		
		ResultSetExtractor<Pakkumine> extractor = new ResultSetExtractor<Pakkumine>() {

			@Override
			public Pakkumine extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String nimetus = rs.getString("nimetus");
					Integer hind = rs.getInt("hind");
					String kirjeldus = rs.getString("kirjeldus");
					Integer perioodid = rs.getInt("perioodid");
					return new Pakkumine(id, nimetus, hind, kirjeldus, perioodid);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM pakkumine WHERE pakkumineid=" + id;
		
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Pakkumine> list() {
		String sql = "SELECT * FROM pakkumine";
		
		RowMapper<Pakkumine> rowMapper = new RowMapper<Pakkumine>() {

			@Override
			public Pakkumine mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("pakkumineid");
				String nimetus = rs.getString("nimetus");
				Integer hind = rs.getInt("hind");
				String kirjeldus = rs.getString("kirjeldus");
				Integer perioodid = rs.getInt("perioodid");
				return new Pakkumine(id, nimetus, hind, kirjeldus, perioodid);			
			}			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Pakkumine> listByPeriood(Integer perioodid) {
		String sql = "SELECT * FROM pakkumine WHERE perioodid=" + perioodid;
		
		RowMapper<Pakkumine> rowMapper = new RowMapper<Pakkumine>() {

			@Override
			public Pakkumine mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("pakkumineid");
				String nimetus = rs.getString("nimetus");
				Integer hind = rs.getInt("hind");
				String kirjeldus = rs.getString("kirjeldus");
				Integer perioodid = rs.getInt("perioodid");
				return new Pakkumine(id, nimetus, hind, kirjeldus, perioodid);			
			}			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}
}
