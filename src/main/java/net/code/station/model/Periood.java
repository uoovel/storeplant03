package net.code.station.model;

import java.sql.Timestamp;

public class Periood {
	private Integer id;
	private Timestamp alates;
	private Timestamp kuni;
	private String perNimetus;	
	public Periood() {
		
	}	
	public Periood(Integer id, Timestamp alates, Timestamp kuni, String perNimetus) {		
		this(alates, kuni, perNimetus);
		this.id = id;
	}
	public Periood(Timestamp alates, Timestamp kuni, String perNimetus) {
		this.alates = alates;
		this.kuni = kuni;
		this.perNimetus = perNimetus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getAlates() {
		return alates;
	}
	public void setAlates(Timestamp alates) {
		this.alates = alates;
	}
	public Timestamp getKuni() {
		return kuni;
	}
	public void setKuni(Timestamp kuni) {
		this.kuni = kuni;
	}
	public String getPerNimetus() {
		return perNimetus;
	}
	public void setPerNimetus(String perNimetus) {
		this.perNimetus = perNimetus;
	}
	@Override
	public String toString() {
		return perNimetus;
	}
	
}
