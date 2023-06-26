package net.code.station.dao;

import java.util.List;

import net.code.station.model.TarbitudEnergia;

public interface TarbitudEnergiaDAO {
	public int save(TarbitudEnergia tarbitudEnergia);

	public List<TarbitudEnergia> list(Integer arvestiid, Integer perioodid);

	public int deleteKirjed(Integer arvestiid, Integer perioodid);

}
