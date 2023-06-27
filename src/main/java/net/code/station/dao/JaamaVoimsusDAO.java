package net.code.station.dao;

import java.util.List;

import net.code.station.model.Contact;
import net.code.station.model.JaamaVoimsus;

public interface JaamaVoimsusDAO {
	public List<JaamaVoimsus> list();

	public Integer getmaxOffer(Integer perioodid);
	
	public int update(JaamaVoimsus jaamaVoimsus);

	public List<JaamaVoimsus> listByPeriood(Integer perioodid);
	
	public int save(JaamaVoimsus jaamaVoimsus);
}
