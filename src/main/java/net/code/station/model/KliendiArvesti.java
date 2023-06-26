package net.code.station.model;

public class KliendiArvesti {
	private Integer id;
	private Integer klientid;
	private Integer arvestiid;
	
	public KliendiArvesti() {
		
	}
	
	public KliendiArvesti(Integer id, Integer klientid, Integer arvestiid) {		
		this(klientid, arvestiid);
		this.id = id;
	}
	public KliendiArvesti(Integer klientid, Integer arvestiid) {
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

	public Integer getArvestiid() {
		return arvestiid;
	}

	public void setArvestiid(Integer arvestiid) {
		this.arvestiid = arvestiid;
	}

	@Override
	public String toString() {
		return "KliendiArvesti [id=" + id + ", klientid=" + klientid + ", arvestiid=" + arvestiid + "]";
	}
	

}
