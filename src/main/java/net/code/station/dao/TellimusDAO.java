package net.code.station.dao;

import java.util.List;

import net.code.station.model.Tellimus;
import net.code.station.viewmodel.TellimusViewL;

public interface TellimusDAO {
	public int save(Tellimus tellimus);
	
	public int update(Tellimus tellimus);
	
	public Tellimus get(Integer id);
	
	public int delete(Integer id);
	
	public List<Tellimus> list();

	public Tellimus getByKlient(Integer klientid, Integer perioodid);

	public List<Tellimus> listByKlient(Integer klientid);

	public Integer getmaxTellimus(Integer perioodid);

	public List<Tellimus> listByKlientPeriood(Integer klientid, Integer perioodid);

	public List<Tellimus> listByPeriood(Integer perioodid);

	public List<Integer> listPerioodIdTellimusOn(Integer klientid);

	public List<TellimusViewL> listLeft(Integer klientid);

}
