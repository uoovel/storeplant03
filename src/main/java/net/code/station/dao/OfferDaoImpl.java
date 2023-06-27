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

import net.code.station.model.Offer;

public class OfferDaoImpl implements OfferDAO{
	private JdbcTemplate jdbcTemplate;
	
	public OfferDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int save(Offer c) {
		String sql = "INSERT INTO pakkumine (nimetus, hind, kirjeldus, perioodid) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, c.getNimetus(), c.getHind(), c.getKirjeldus(), c.getPerioodid());
	}

	@Override
	public int update(Offer c) {
		String sql = "UPDATE pakkumine SET nimetus=?, hind=?, kirjeldus=?, perioodid=? WHERE pakkumineid=?";
		
		return jdbcTemplate.update(sql, c.getNimetus(), c.getHind(), c.getKirjeldus(), c.getPerioodid(), c.getId());
	}

	@Override
	public Offer get(Integer id) {
		String sql = "SELECT * FROM pakkumine WHERE pakkumineid=" + id;
		
		ResultSetExtractor<Offer> extractor = new ResultSetExtractor<Offer>() {

			@Override
			public Offer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String nimetus = rs.getString("nimetus");
					Integer hind = rs.getInt("hind");
					String kirjeldus = rs.getString("kirjeldus");
					Integer perioodid = rs.getInt("perioodid");
					return new Offer(id, nimetus, hind, kirjeldus, perioodid);
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
	public List<Offer> list() {
		String sql = "SELECT * FROM pakkumine";
		
		RowMapper<Offer> rowMapper = new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("pakkumineid");
				String nimetus = rs.getString("nimetus");
				Integer hind = rs.getInt("hind");
				String kirjeldus = rs.getString("kirjeldus");
				Integer perioodid = rs.getInt("perioodid");
				return new Offer(id, nimetus, hind, kirjeldus, perioodid);			
			}			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Offer> listByPeriood(Integer perioodid) {
		String sql = "SELECT * FROM pakkumine WHERE perioodid=" + perioodid;
		
		RowMapper<Offer> rowMapper = new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("pakkumineid");
				String nimetus = rs.getString("nimetus");
				Integer hind = rs.getInt("hind");
				String kirjeldus = rs.getString("kirjeldus");
				Integer perioodid = rs.getInt("perioodid");
				return new Offer(id, nimetus, hind, kirjeldus, perioodid);			
			}			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}
}
