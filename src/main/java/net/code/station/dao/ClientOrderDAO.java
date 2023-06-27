package net.code.station.dao;

import java.util.List;

import net.code.station.model.ClientOrder;
import net.code.station.viewmodel.ClientOrderViewL;

public interface ClientOrderDAO {
	public int save(ClientOrder tellimus);
	
	public int update(ClientOrder tellimus);
	
	public ClientOrder get(Integer id);
	
	public int delete(Integer id);
	
	public List<ClientOrder> list();

	public ClientOrder getByKlient(Integer klientid, Integer perioodid);

	public List<ClientOrder> listByKlient(Integer klientid);

	public Integer getmaxClientOrder(Integer perioodid);

	public List<ClientOrder> listByKlientPeriood(Integer klientid, Integer perioodid);

	public List<ClientOrder> listByPeriood(Integer perioodid);

	public List<Integer> listPerioodIdClientOrderOn(Integer klientid);

	public List<ClientOrderViewL> listLeft(Integer klientid);

}
