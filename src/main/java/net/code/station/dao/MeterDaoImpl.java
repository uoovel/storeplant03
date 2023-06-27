package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import net.code.station.model.Meter;
import net.code.station.model.Contact;

public class MeterDaoImpl implements MeterDAO{

    private JdbcTemplate jdbcTemplate;
	
	public MeterDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Meter get(Integer id) {
		String sql = "SELECT * FROM arvesti WHERE arvestiid=" + id;		
		ResultSetExtractor<Meter> extractor = new ResultSetExtractor<Meter>() {
			@Override
			public Meter extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String number = rs.getString("number");
					Integer peavoimsus = rs.getInt("peavoimsus");
					Integer bilansihaldurid = rs.getInt("bilansihaldurid");
					String ipaddress = rs.getString("ipaddress");
					
					
					return new Meter(id, number, peavoimsus, bilansihaldurid, ipaddress);
				}
				return null;
			}
		};		
		return jdbcTemplate.query(sql, extractor);
	}

}
