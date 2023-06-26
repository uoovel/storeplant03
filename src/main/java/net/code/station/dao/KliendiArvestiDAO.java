package net.code.station.dao;

import java.util.List;

import net.code.station.model.KliendiArvesti;

public interface KliendiArvestiDAO {
	public KliendiArvesti getArvestiId(Integer klientid);

	public KliendiArvesti getArvestiByArvestiId(Integer arvestiid);

	public List<KliendiArvesti> getListByKlientId(Integer klientid);
}
