package net.code.station.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.code.station.model.Offer;
import net.code.station.model.ClientOrder;
import net.code.station.viewmodel.ClientOrderViewL;

public class ClientOrderDaoImpl implements ClientOrderDAO {
	private JdbcTemplate jdbcTemplate;
	
	public ClientOrderDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int save(ClientOrder c) {
		String sql = "INSERT INTO tellimus (pakkumineid, klientid, alates, kuni, perioodid, staatusid, arvestiid) VALUES (?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, c.getOfferid(),
				c.getKlientid(), c.getAlates(), c.getKuni(),
				c.getPerioodid(), c.getStaatusid(), c.getMeterid());
	}

	@Override
	public int update(ClientOrder c) {
		String sql = "UPDATE tellimus SET pakkumineid=?, klientid=?, alates=?, kuni=?, perioodid=?, staatusid=?, arvestiid=? WHERE tellimusid=?";
		
		return jdbcTemplate.update(sql, c.getOfferid(), 
				c.getKlientid(), c.getAlates(), c.getKuni(),
				c.getPerioodid(), c.getStaatusid(), c.getMeterid(), c.getId());
	}

	@Override
	public ClientOrder get(Integer id) {
		String sql = "SELECT * FROM tellimus WHERE tellimusid=" + id;
		
		ResultSetExtractor<ClientOrder> extractor = new ResultSetExtractor<ClientOrder>() {

			@Override
			public ClientOrder extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer pakkumineid = rs.getInt("pakkumineid");					
					Integer klientid = rs.getInt("klientid");
					Date alates = rs.getDate("alates");
					Date kuni = rs.getDate("kuni");
					Integer perioodid = rs.getInt("perioodid");
					Integer staatusid = rs.getInt("staatusid");
					Integer arvestiid = rs.getInt("arvestiid");
					return new ClientOrder(id, pakkumineid, klientid, alates, kuni, 
							perioodid, staatusid, arvestiid, null);
				}
				return null;
			}	
			
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM tellimus WHERE tellimusid=" + id;
		
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<ClientOrder> list() {
		String sql = "SELECT * FROM tellimus";
		
		RowMapper<ClientOrder> rowMapper = new RowMapper<ClientOrder>() {

			@Override
			public ClientOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("tellimusid");
				Integer pakkumineid = rs.getInt("pakkumineid");
				Integer klientid = rs.getInt("klientid");
				Date alates = rs.getDate("alates");
				Date kuni = rs.getDate("kuni");
				Integer perioodid = rs.getInt("perioodid");
				Integer staatusid = rs.getInt("staatusid");
				Integer arvestiid = rs.getInt("arvestiid");
				ClientOrder tellimus = new ClientOrder(id, pakkumineid, klientid, alates, kuni, 
						perioodid, staatusid, arvestiid, null);				
				return tellimus;
				//return new ClientOrder(id, pakkumineid, klientid, alates, kuni, 
						//perioodid, staatusid);			
			}			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public ClientOrder getByKlient(Integer klientid, Integer perioodid) {
		String sql = "SELECT * FROM tellimus WHERE klientid=" + klientid + " AND perioodid=" + perioodid;
		
		ResultSetExtractor<ClientOrder> extractor = new ResultSetExtractor<ClientOrder>() {

			@Override
			public ClientOrder extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("tellimusid");
					Integer pakkumineid = rs.getInt("pakkumineid");					
					Integer klientid = rs.getInt("klientid");
					Date alates = rs.getDate("alates");
					Date kuni = rs.getDate("kuni");
					Integer perioodid = rs.getInt("perioodid");
					Integer staatusid = rs.getInt("staatusid");
					Integer arvestiid = rs.getInt("arvestiid");
					ClientOrder tellimus = new ClientOrder(id, pakkumineid, klientid, alates, kuni, 
							perioodid, staatusid, arvestiid, null);
					System.out.println("getByKlient>tellimus: " + tellimus);
					return tellimus;
					//return new ClientOrder(id, pakkumineid, klientid, alates, kuni, 
							//perioodid, staatusid);
				}
				return null;
			}	
			
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public List<ClientOrder> listByKlient(Integer klientid) {
        String sql = "SELECT * FROM tellimus WHERE klientid=" + klientid;
		
		RowMapper<ClientOrder> rowMapper = new RowMapper<ClientOrder>() {

			@Override
			public ClientOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("tellimusid");
				Integer pakkumineid = rs.getInt("pakkumineid");
				Integer klientid = rs.getInt("klientid");
				Date alates = rs.getDate("alates");
				Date kuni = rs.getDate("kuni");
				Integer perioodid = rs.getInt("perioodid");
				Integer staatusid = rs.getInt("staatusid");
				Integer arvestiid = rs.getInt("arvestiid");
				return new ClientOrder(id, pakkumineid, klientid, alates, kuni, 
						perioodid, staatusid, arvestiid, null);			
			}			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Integer getmaxClientOrder(Integer perioodid) {
		String sql = "SELECT COUNT(pakkumineid) AS maxtellimus FROM tellimus WHERE perioodid=" + perioodid;
		
		ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer maxtellimus = rs.getInt("maxtellimus");
					
					return maxtellimus;
				}
				return null;
			}	
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public List<ClientOrder> listByKlientPeriood(Integer klientid, Integer perioodid) {
	       String sql = "SELECT * FROM tellimus WHERE klientid=" + klientid + " AND perioodid=" + perioodid;
			
			RowMapper<ClientOrder> rowMapper = new RowMapper<ClientOrder>() {

				@Override
				public ClientOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
					Integer id = rs.getInt("tellimusid");
					Integer pakkumineid = rs.getInt("pakkumineid");
					Integer klientid = rs.getInt("klientid");
					Date alates = rs.getDate("alates");
					Date kuni = rs.getDate("kuni");
					Integer perioodid = rs.getInt("perioodid");
					Integer staatusid = rs.getInt("staatusid");
					Integer arvestiid = rs.getInt("arvestiid");
					return new ClientOrder(id, pakkumineid, klientid, alates, kuni, 
							perioodid, staatusid, arvestiid, null);			
				}			
			};
			
			return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<ClientOrder> listByPeriood(Integer perioodid) {
	       String sql = "SELECT * FROM tellimus WHERE perioodid=" + perioodid;
			
			RowMapper<ClientOrder> rowMapper = new RowMapper<ClientOrder>() {

				@Override
				public ClientOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
					Integer id = rs.getInt("tellimusid");
					Integer pakkumineid = rs.getInt("pakkumineid");
					Integer klientid = rs.getInt("klientid");
					Date alates = rs.getDate("alates");
					Date kuni = rs.getDate("kuni");
					Integer perioodid = rs.getInt("perioodid");
					Integer staatusid = rs.getInt("staatusid");
					Integer arvestiid = rs.getInt("arvestiid");
					return new ClientOrder(id, pakkumineid, klientid, alates, kuni, 
							perioodid, staatusid, arvestiid, null);			
				}			
			};
			
			return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Integer> listPerioodIdClientOrderOn(Integer klientid) {
	       String sql = "SELECT * FROM tellimus WHERE klientid=" + klientid;
			
			RowMapper<Integer> rowMapper = new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					//Integer id = rs.getInt("tellimusid");
					//Integer pakkumineid = rs.getInt("pakkumineid");
					//Integer klientid = rs.getInt("klientid");
					//Date alates = rs.getDate("alates");
					//Date kuni = rs.getDate("kuni");
					Integer perioodid = rs.getInt("perioodid");
					//Integer staatusid = rs.getInt("staatusid");
					//Integer arvestiid = rs.getInt("arvestiid");
					return perioodid;			
				}			
			};
			
			return jdbcTemplate.query(sql, rowMapper);
	}
    //javatpoint.com/sql-select-from-multiple-tables
	@Override
	public List<ClientOrderViewL> listLeft(Integer klientid) {
        //String sql = "SELECT * FROM tellimus";	//jarg	
        //String sql = "SELECT t.tellimusid, c.firstname FROM tellimus AS t LEFT JOIN contact AS c ON t.klientid=c.contactid";
        String sql = "SELECT te.tellimusid, pa.nimetus, pa.hind, pa.kirjeldus, co.firstname, co.lastname, pe.alates, pe.pernimetus, st.nimetus AS staNimetus "
        		+ "FROM tellimus AS te "
        		+ "LEFT JOIN pakkumine AS pa "
        		+ "ON te.pakkumineid=pa.pakkumineid "
        		+ "LEFT JOIN contact AS co "
        		+ "ON te.klientid=co.contactid "
        		+ "LEFT JOIN periood AS pe "
        		+ "ON te.perioodid=pe.perioodid "
        		+ "LEFT JOIN staatus AS st "
        		+ "ON te.staatusid=st.staatusid "
        		+ "WHERE te.klientid=" + klientid;		
        
        RowMapper<ClientOrderViewL> rowMapper = new RowMapper<ClientOrderViewL>() {
			@Override
			public ClientOrderViewL mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("tellimusid");
				String pakNimetus = rs.getString("nimetus");
				Integer hind = rs.getInt("hind");
				String kirjeldus = rs.getString("kirjeldus");
				String eesnimi = rs.getString("firstname");
				String perenimi = rs.getString("lastname");
				Timestamp alates = rs.getTimestamp("alates");
				String perNimetus = rs.getString("pernimetus");
				String staNimetus = rs.getString("staNimetus");
				return new ClientOrderViewL(id, pakNimetus, hind, kirjeldus,
						eesnimi, perenimi, alates, perNimetus, staNimetus);			
			}			
		};
		return jdbcTemplate.query(sql, rowMapper);
	}
}
