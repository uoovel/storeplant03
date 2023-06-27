package net.code.station.model;

public class ClientMeter {
	private Integer id;
	private Integer klientid;
	private Integer arvestiid;
	
	public ClientMeter() {
		
	}
	
	public ClientMeter(Integer id, Integer klientid, Integer arvestiid) {		
		this(klientid, arvestiid);
		this.id = id;
	}
	public ClientMeter(Integer klientid, Integer arvestiid) {
		this.klientid = klientid;
		this.arvestiid = arvestiid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKlientid() {
		return klientid;
	}

	public void setKlientid(Integer klientid) {
		this.klientid = klientid;
	}

	public Integer getMeterid() {
		return arvestiid;
	}

	public void setMeterid(Integer arvestiid) {
		this.arvestiid = arvestiid;
	}

	@Override
	public String toString() {
		return "ClientMeter [id=" + id + ", klientid=" + klientid + ", arvestiid=" + arvestiid + "]";
	}
	

}
