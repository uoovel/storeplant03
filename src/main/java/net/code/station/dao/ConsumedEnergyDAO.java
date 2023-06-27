package net.code.station.dao;

import java.util.List;

import net.code.station.model.ConsumedEnergy;

public interface ConsumedEnergyDAO {
	public int save(ConsumedEnergy tarbitudEnergia);

	public List<ConsumedEnergy> list(Integer arvestiid, Integer perioodid);

	public int deleteKirjed(Integer arvestiid, Integer perioodid);

}
