package net.code.station.dao;

import java.util.List;

import net.code.station.model.Offer;

public interface OfferDAO {
	public int save(Offer pakkumine);
	
	public int update(Offer pakkumine);
	
	public Offer get(Integer id);
	
	public int delete(Integer id);
	
	public List<Offer> list();

	public List<Offer> listByPeriood(Integer perioodid);

}
