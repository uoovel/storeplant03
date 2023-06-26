package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import net.code.station.model.Contact;
import net.code.station.model.KasutajaRollis;

public class KasutajaRollisDaoImpl implements KasutajaRollisDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public KasutajaRollisDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public KasutajaRollis getByUserId(Integer userId) {
        String sql = "SELECT * FROM kasutajarollis WHERE kasutajaid=" + userId;
		
		ResultSetExtractor<KasutajaRollis> extractor = new ResultSetExtractor<KasutajaRollis>() {

			@Override
			public KasutajaRollis extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					//jarg
			        Integer id = rs.getInt("kasutajarollisid");
			        Integer kasutajaid = rs.getInt("kasutajaid");
			        Integer rollid = rs.getInt("rollid");
					
					return new KasutajaRollis(id, kasutajaid, rollid);
				}
				return null;
			}
		
			
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

}
