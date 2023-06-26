package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import net.code.station.model.Hind;
import net.code.station.model.KliendiArvesti;

public class HindDaoImpl implements HindDAO {
	private JdbcTemplate jdbcTemplate;
	
	public HindDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Hind get(Integer perioodid) {
		String sql = "SELECT * FROM hind WHERE perioodid=" + perioodid;
		
		ResultSetExtractor<Hind> extractor = new ResultSetExtractor<Hind>() {

			@Override
			public Hind extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("hindid");
					Integer vaartus = rs.getInt("hind");
					Integer perioodid = rs.getInt("perioodid");					
					return new Hind(id, vaartus, perioodid);
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}
}
