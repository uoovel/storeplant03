package net.code.station.model;

import java.sql.Time;
import java.sql.Timestamp;

public class JaamaVoimsus {
	private Integer id;
	private Integer perioodid;
	private Time aeg;
	private Integer pakutav;
	private Integer tellitud;
	private Integer jaak;
	private Integer too;
	private Double koormusTegur;
	
    public JaamaVoimsus() {
		
	}
	
	public JaamaVoimsus(Integer id, Integer perioodid, Time aeg, 
			Integer pakutav, Integer tellitud, Integer jaak, 
			Integer too, Double koormusTegur) {		
		this(perioodid, aeg, pakutav, tellitud, jaak, too, koormusTegur);
		this.id = id;
	}
	public JaamaVoimsus(Integer perioodid, Time aeg, 
			Integer pakutav, Integer tellitud, Integer jaak, 
			Integer too, Double koormusTegur) {
		this.perioodid = perioodid;	
		this.aeg = aeg;
		this.pakutav = pakutav;
		this.tellitud = tellitud;
		this.jaak = jaak;
		this.too = too;
		this.koormusTegur = koormusTegur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPerioodid() {
		return perioodid;
	}

	public void setPerioodid(Integer perioodid) {
		this.perioodid = perioodid;
	}

	public Time getAeg() {
		return aeg;
	}

	public void setAeg(Time aeg) {
		this.aeg = aeg;
	}

	public Integer getPakutav() {
		return pakutav;
	}

	public void setPakutav(Integer pakutav) {
		this.pakutav = pakutav;
	}

	public Integer getTellitud() {
		return tellitud;
	}

	public void setTellitud(Integer tellitud) {
		this.tellitud = tellitud;
	}

	public Integer getJaak() {
		return jaak;
	}

	public void setJaak(Integer jaak) {
		this.jaak = jaak;
	}

	public Integer getToo() {
		return too;
	}

	public void setToo(Integer too) {
		this.too = too;
	}
	

	public Double getKoormusTegur() {
		return koormusTegur;
	}

	public void setKoormusTegur(Double koormusTegur) {
		this.koormusTegur = koormusTegur;
	}

	@Override
	public String toString() {
		return "JaamaVoimsus [id=" + id + ", perioodid=" + perioodid + ", aeg=" + aeg + ", pakutav=" + pakutav
				+ ", tellitud=" + tellitud + ", jaak=" + jaak + ", too=" + too + "]";
	}
	

}
