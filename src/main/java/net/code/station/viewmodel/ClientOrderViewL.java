package net.code.station.viewmodel;

import java.sql.Date;
import java.sql.Timestamp;

public class ClientOrderViewL {
	private Integer id;
	private String pakNimetus;
	private Integer hind;
	private String kirjeldus;
	private String eesnimi;
	private String perenimi;
	private Timestamp alates;
	private String perNimetus;
	private String staNimetus;	
	public ClientOrderViewL() {
		
	}
	public ClientOrderViewL(Integer id, String pakNimetus,	Integer hind,
			String kirjeldus, String eesnimi, String perenimi,
			Timestamp alates, String perNimetus, String staNimetus) {
		this(pakNimetus, hind, kirjeldus, eesnimi, perenimi,alates,
				perNimetus, staNimetus);
		this.id = id;
	}
	public ClientOrderViewL(String pakNimetus, Integer hind, 
			String kirjeldus, String eesnimi, String perenimi, 
			Timestamp alates, String perNimetus, String staNimetus) {
		this.pakNimetus = pakNimetus;
		this.hind = hind;
		this.kirjeldus = kirjeldus;
		this.eesnimi = eesnimi;
		this.perenimi = perenimi;
		this.alates = alates;
		this.perNimetus = perNimetus;
		this.staNimetus = staNimetus;
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
	public String getPerenimi() {
		return perenimi;
	}
	public void setPerenimi(String perenimi) {
		this.perenimi = perenimi;
	}
	public Timestamp getAlates() {
		return alates;
	}
	public void setAlates(Timestamp alates) {
		this.alates = alates;
	}
	public String getPakNimetus() {
		return pakNimetus;
	}
	public void setPakNimetus(String pakNimetus) {
		this.pakNimetus = pakNimetus;
	}
	public String getPerNimetus() {
		return perNimetus;
	}
	public void setPerNimetus(String perNimetus) {
		this.perNimetus = perNimetus;
	}
	public String getStaNimetus() {
		return staNimetus;
	}
	public void setStaNimetus(String staNimetus) {
		this.staNimetus = staNimetus;
	}
	public String getEesnimi() {
		return eesnimi;
	}
	public void setEesnimi(String eesnimi) {
		this.eesnimi = eesnimi;
	}
	public ClientOrderViewL(String eesnimi) {
		this.eesnimi = eesnimi;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ClientOrderViewL [id=" + id + "]";
	}
}
