package net.code.station.dao;

import java.sql.Timestamp;
import java.util.List;

import net.code.station.model.MeterState;

public interface MeterStateDAO {
	//public int save(Tellimus tellimus);
	
	//public int update(Tellimus tellimus);
	
	public MeterState get(Integer id);
	
	//public int delete(Integer id);
	
	public List<MeterState> list(Integer arvestiid);

	public List<MeterState> listForPeriod(Integer arvestiid, Timestamp algus, Timestamp lopp);
}
