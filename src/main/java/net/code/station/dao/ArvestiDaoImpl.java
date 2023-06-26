package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import net.code.station.model.Arvesti;
import net.code.station.model.Contact;

public class ArvestiDaoImpl implements ArvestiDAO{

    private JdbcTemplate jdbcTemplate;
	
	public ArvestiDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Arvesti get(Integer id) {
		String sql = "SELECT * FROM arvesti WHERE arvestiid=" + id;		
		ResultSetExtractor<Arvesti> extractor = new ResultSetExtractor<Arvesti>() {
			@Override
			public Arvesti extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String number = rs.getString("number");
					Integer peavoimsus = rs.getInt("peavoimsus");
					Integer bilansihaldurid = rs.getInt("bilansihaldurid");
					String ipaddress = rs.getString("ipaddress");
					
					
					return new Arvesti(id, number, peavoimsus, bilansihaldurid, ipaddress);
				}
				return null;
			}
		};		
		return jdbcTemplate.query(sql, extractor);
	}

}
