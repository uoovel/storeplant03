package net.code.station.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import net.code.station.model.ConsumedEnergy;

public class ConsumedEnergyDaoImpl implements ConsumedEnergyDAO {
	private JdbcTemplate jdbcTemplate;	
	public ConsumedEnergyDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}	
	@Override
	public int save(ConsumedEnergy t) {
		String sql = "INSERT INTO tarbitudenergia ("
				+ "arvestiid, alates, kuni, energia, hind, summa, pernimetus, perioodid)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, t.getArvestiid(), t.getAlates(), 
				t.getKuni(), t.getEnergia(), t.getHind(), t.getSumma(), 
				t.getPerNimetus(), t.getPerioodid());
	}
	@Override
	public List<ConsumedEnergy> list(Integer arvestiid, Integer perioodid) {
		String sql = "SELECT * FROM tarbitudenergia WHERE arvestiid=" + arvestiid
				+ " AND perioodid=" + perioodid;		
		RowMapper<ConsumedEnergy> rowMapper = new RowMapper<ConsumedEnergy>() {
			@Override
			public ConsumedEnergy mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("tarbitudenergiaid");
				Integer arvestiid = rs.getInt("arvestiid");
				Timestamp alates = rs.getTimestamp("alates");
				Timestamp kuni = rs.getTimestamp("kuni");
				Integer energia = rs.getInt("energia");
				Integer hind = rs.getInt("hind");
				Integer summa = rs.getInt("summa");
				String perNimetus = rs.getString("pernimetus");
			    Integer perioodid = rs.getInt("perioodid");
				return new ConsumedEnergy(id, arvestiid, alates, kuni, energia, 
						hind, summa, perNimetus, perioodid);			
			}			
		};		
		return jdbcTemplate.query(sql, rowMapper);
	}
	@Override
	public int deleteKirjed(Integer arvestiid, Integer perioodid) {
        String sql = "DELETE FROM tarbitudenergia WHERE arvestiid=" + arvestiid + " AND perioodid=" + perioodid;
		
		return jdbcTemplate.update(sql);
	}
}
