package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;


import net.code.station.model.EnergyProvider;

public class EnergyProviderDaoImpl implements EnergyProviderDAO {
	private JdbcTemplate jdbcTemplate;
	
	public EnergyProviderDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public EnergyProvider get(Integer bilansihaldurid) {
		String sql = "SELECT * FROM bilansihaldur WHERE bilansihaldurid=" + bilansihaldurid;		
		ResultSetExtractor<EnergyProvider> extractor = new ResultSetExtractor<EnergyProvider>() {
			@Override
			public EnergyProvider extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String nimetus = rs.getString("nimetus");
					
					
					
					return new EnergyProvider(bilansihaldurid, nimetus);
				}
				return null;
			}
		};		
		return jdbcTemplate.query(sql, extractor);
	}

}
