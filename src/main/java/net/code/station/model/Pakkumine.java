package net.code.station.model;

import java.sql.Date;

public class Pakkumine {
	private Integer id;
	private String nimetus;
	private Integer hind;
	private String kirjeldus;
	private Integer perioodid;	
	
	//private String pakkuminenimi;
	
	public Pakkumine() {
		
	}
	
	public Pakkumine(Integer id, String nimetus, Integer hind, String kirjeldus, Integer perioodid) {		
		this(nimetus, hind, kirjeldus, perioodid);
		this.id = id;
		
		//this.pakkuminenimi = "Max voimsus: " + voimsus.toString() + "kW";
	}
	public Pakkumine(String nimetus, Integer hind, String kirjeldus, Integer perioodid) {
		this.nimetus = nimetus;
		this.hind = hind;
		this.kirjeldus = kirjeldus;
		this.perioodid = perioodid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNimetus() {
		return nimetus;
	}

	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}

	public Integer getHind() {
		return hind;
	}

	public void setHind(Integer hind) {
		this.hind = hind;
	}

	public String getKirjeldus() {
		return kirjeldus;
	}

	public void setKirjeldus(String kirjeldus) {
		this.kirjeldus = kirjeldus;
	}

	public Integer getPerioodid() {
		return perioodid;
	}

	public void setPerioodid(Integer perioodid) {
		this.perioodid = perioodid;
	}

	@Override
	public String toString() {
		return "<b>Pakett:</b> " + nimetus + ",<br> <b>Hind:</b> " + hind + " [s/kWh], <br><b>Kirjeldus:</b> " + kirjeldus;
	}
	
	
}
