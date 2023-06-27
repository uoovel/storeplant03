package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;


import net.code.station.model.Bilansihaldur;

public class BilansihaldurDaoImpl implements BilansihaldurDAO {
	private JdbcTemplate jdbcTemplate;
	
	public BilansihaldurDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Bilansihaldur get(Integer bilansihaldurid) {
		String sql = "SELECT * FROM bilansihaldur WHERE bilansihaldurid=" + bilansihaldurid;		
		ResultSetExtractor<Bilansihaldur> extractor = new ResultSetExtractor<Bilansihaldur>() {
			@Override
			public Bilansihaldur extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String nimetus = rs.getString("nimetus");
					
					
					
					return new Bilansihaldur(bilansihaldurid, nimetus);
				}
				return null;
			}
		};		
		return jdbcTemplate.query(sql, extractor);
	}

}
