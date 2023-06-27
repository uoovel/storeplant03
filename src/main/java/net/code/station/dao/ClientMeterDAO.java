package net.code.station.dao;

import java.util.List;

import net.code.station.model.ClientMeter;

public interface ClientMeterDAO {
	public ClientMeter getMeterId(Integer klientid);

	public ClientMeter getArvestiByArvestiId(Integer arvestiid);

	public List<ClientMeter> getListByKlientId(Integer klientid);
}
