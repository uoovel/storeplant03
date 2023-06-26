package net.code.station.model;

import java.sql.Date;


import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class Tellimus {
	private Integer id;	
	@Min(value=1, message = "Palun vali pakkumine")
	private Integer pakkumineid;
	private Integer klientid;
	private Date alates;
	private Date kuni;
	@Min(value=1, message = "Palun vali periood")
	private Integer perioodid;
	private Integer staatusid;
	@Min(value=1, message = "Palun vali arvesti")
	private Integer arvestiid;
	private String userName;
	
	public Tellimus() {
		
	}
	
	public Tellimus(Integer id, Integer pakkumineid, Integer klientid, 
			Date alates, Date kuni, Integer perioodid, Integer staatusid, 
			Integer arvestiid, String userName) {		
		this(pakkumineid, klientid, alates, kuni, 
				perioodid, staatusid, arvestiid, userName);
		this.id = id;
	}
	public Tellimus(Integer pakkumineid, Integer klientid, 
			Date alates, Date kuni,
			Integer perioodid, Integer staatusid, Integer arvestiid, String userName) {
		this.pakkumineid = pakkumineid;
		this.klientid = klientid;
		this.alates = alates;
		this.kuni = kuni;
		this.perioodid = perioodid;
		this.staatusid = staatusid;
		this.arvestiid = arvestiid;
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPakkumineid() {
		return pakkumineid;
	}

	public void setPakkumineid(Integer pakkumineid) {
		this.pakkumineid = pakkumineid;
	}

	public Integer getKlientid() {
		return klientid;
	}

	public void setKlientid(Integer klientid) {
		this.klientid = klientid;
	}
	
	public Date getAlates() {
		return alates;
	}

	public void setAlates(Date alates) {
		this.alates = alates;
	}
	
	public Date getKuni() {
		return kuni;
	}

	public void setKuni(Date kuni) {
		this.kuni = kuni;
	}
	
 
	public Integer getPerioodid() {
		return perioodid;
	}

	public void setPerioodid(Integer perioodid) {
		this.perioodid = perioodid;
	}

	public Integer getStaatusid() {
		return staatusid;
	}

	public void setStaatusid(Integer staatusid) {
		this.staatusid = staatusid;
	}
	

	public Integer getArvestiid() {
		return arvestiid;
	}

	public void setArvestiid(Integer arvestiid) {
		this.arvestiid = arvestiid;
	}

	@Override
	public String toString() {
		return "Tellimus [id=" + id + ", pakkumineid=" + pakkumineid + ", klientid=" + klientid + ", alates=" + alates + ", kuni=" + kuni + "]";
	}
	
	
}
