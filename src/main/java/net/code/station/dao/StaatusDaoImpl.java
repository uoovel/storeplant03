package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.code.station.model.Contact;
import net.code.station.model.Hind;
import net.code.station.model.Staatus;

public class StaatusDaoImpl implements StaatusDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public StaatusDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Staatus get(Integer id) {
		String sql = "SELECT * FROM staatus WHERE staatusid=" + id;
		
		ResultSetExtractor<Staatus> extractor = new ResultSetExtractor<Staatus>() {

			@Override
			public Staatus extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("staatusid");
					String nimetus = rs.getString("nimetus");								
					return new Staatus(id, nimetus);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public List<Staatus> list() {
		String sql = "SELECT * FROM staatus";
		
		RowMapper<Staatus> rowMapper = new RowMapper<Staatus>() {

			@Override
			public Staatus mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("staatusid");
				String nimetus = rs.getString("nimetus");				
				return new Staatus(id, nimetus);			
			}			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

}
