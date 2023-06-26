package net.code.station.dao;

import java.util.List;

import net.code.station.model.Staatus;

public interface StaatusDAO {
	public Staatus get(Integer id);

	public List<Staatus> list();
	
}
