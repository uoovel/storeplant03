package net.code.station.model;

import javax.validation.constraints.Min;

public class Hind {
	private Integer id;
	@Min(value=1, message="Minimum 1")
	private Integer vaartus;	
	private Integer perioodid;
	
	public Hind() {
		
	}	
	public Hind(Integer id, Integer vaartus, Integer perioodid) {		
		this(vaartus, perioodid);
		this.id = id;
	}
	public Hind(Integer vaartus, Integer perioodid) {
		this.vaartus = vaartus;
		this.perioodid = perioodid;		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVaartus() {
		return vaartus;
	}
	public void setVaartus(Integer vaartus) {
		this.vaartus = vaartus;
	}
	public Integer getPerioodid() {
		return perioodid;
	}
	public void setPerioodid(Integer perioodid) {
		this.perioodid = perioodid;
	}
	@Override
	public String toString() {
		return "Hind [id=" + id + ", vaartus=" + vaartus + ", perioodid=" + perioodid + "]";
	}
	

}
