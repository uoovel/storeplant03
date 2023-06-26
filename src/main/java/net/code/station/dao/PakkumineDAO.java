package net.code.station.dao;

import java.util.List;

import net.code.station.model.Pakkumine;

public interface PakkumineDAO {
	public int save(Pakkumine pakkumine);
	
	public int update(Pakkumine pakkumine);
	
	public Pakkumine get(Integer id);
	
	public int delete(Integer id);
	
	public List<Pakkumine> list();

	public List<Pakkumine> listByPeriood(Integer perioodid);

}
