package net.code.station.model;

public class KasutajaRollis {
	private Integer id;
	private Integer kasutajaid;
	private Integer rollid;
	
	public KasutajaRollis() {
		
	}
	
	public KasutajaRollis(Integer id, Integer kasutajaid, Integer rollid) {		
		this(kasutajaid, rollid);
		this.id = id;
	}
	public KasutajaRollis(Integer kasutajaid, Integer rollid) {
		this.kasutajaid = kasutajaid;
		this.rollid = rollid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKasutajaid() {
		return kasutajaid;
	}

	public void setKasutajaid(Integer kasutajaid) {
		this.kasutajaid = kasutajaid;
	}

	public Integer getRollid() {
		return rollid;
	}

	public void setRollid(Integer rollid) {
		this.rollid = rollid;
	}
	
}
