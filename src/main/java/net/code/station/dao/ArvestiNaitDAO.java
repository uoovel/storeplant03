package net.code.station.dao;

import java.sql.Timestamp;
import java.util.List;

import net.code.station.model.ArvestiNait;

public interface ArvestiNaitDAO {
	//public int save(Tellimus tellimus);
	
	//public int update(Tellimus tellimus);
	
	public ArvestiNait get(Integer id);
	
	//public int delete(Integer id);
	
	public List<ArvestiNait> list(Integer arvestiid);

	public List<ArvestiNait> listForPeriod(Integer arvestiid, Timestamp algus, Timestamp lopp);
}
