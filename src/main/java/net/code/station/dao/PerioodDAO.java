package net.code.station.dao;

import java.util.List;

import net.code.station.model.Periood;

public interface PerioodDAO {
	public List<Periood> list();

	public Periood get(Integer perioodid);

	public Integer getMaxPakutav(Integer perioodid);
}
