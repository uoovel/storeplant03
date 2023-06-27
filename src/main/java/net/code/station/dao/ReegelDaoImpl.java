package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;


import net.code.station.model.Reegel;

public class ReegelDaoImpl implements ReegelDAO{
	private JdbcTemplate jdbcTemplate;
	
	public ReegelDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Reegel getActiveRule() {
		String sql = "SELECT * FROM reegel WHERE kehtib=1";		
		ResultSetExtractor<Reegel> extractor = new ResultSetExtractor<Reegel>() {
			@Override
			public Reegel extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("reegelid");
					String nimetus = rs.getString("nimetus");
					Boolean kehtib = rs.getBoolean("kehtib");
					String kirjeldus = rs.getString("kirjeldus");
					return new Reegel(id, nimetus, kehtib, kirjeldus);
				}
				return null;
			}
		};		
		return jdbcTemplate.query(sql, extractor);
	}
}
