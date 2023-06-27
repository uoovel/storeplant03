package net.code.station.viewmodel;

import net.code.station.model.Periood;

public class OfferView {
	private Integer id;
	private String nimetus;
	private Integer hind;
	private String kirjeldus;
	private Periood periood;
	public OfferView() {
		
	}
	
	public OfferView(Integer id, String nimetus, Integer hind, String kirjeldus, Periood periood) {		
		this(nimetus, hind, kirjeldus, periood);
		this.id = id;
		
		//this.pakkuminenimi = "Max voimsus: " + voimsus.toString() + "kW";
	}
	public OfferView(String nimetus, Integer hind, String kirjeldus, Periood periood) {
		this.nimetus = nimetus;
		this.hind = hind;
		this.kirjeldus = kirjeldus;
		this.periood = periood;
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
	public Periood getPeriood() {
		return periood;
	}
	public void setPeriood(Periood periood) {
		this.periood = periood;
	}
	@Override
	public String toString() {
		return "OfferView [id=" + id + ", nimetus=" + nimetus + ", hind=" + hind + ", kirjeldus=" + kirjeldus
				+ ", periood=" + periood + "]";
	}
	

}
