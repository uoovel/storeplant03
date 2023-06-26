package net.code.station.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.Min;

import org.springframework.format.datetime.standard.DateTimeContext;

public class TarbitudEnergia {
	private Integer id;	
	private Integer arvestiid;	
	private Timestamp alates;
	private Timestamp kuni;
	private Integer energia;
	private Integer hind;
	private Integer summa;
	private String perNimetus;
	@Min(value=1, message="Palun vali periood")
	private Integer perioodid;
	
	public TarbitudEnergia() {
		
	}
	
	public TarbitudEnergia(Integer id, Integer arvestiid, Timestamp alates, 
			Timestamp kuni, Integer energia, Integer hind, 
			Integer summa, String perNimetus, Integer perioodid) {		
		this(arvestiid, alates, kuni, energia, hind, summa, perNimetus, perioodid);
		this.id = id;
	}
	public TarbitudEnergia(Integer arvestiid, Timestamp alates, 
			Timestamp kuni, Integer energia, Integer hind, 
			Integer summa, String perNimetus, Integer perioodid) {
		this.arvestiid = arvestiid;		
		this.alates = alates;
		this.kuni = kuni;
		this.energia = energia;
		this.hind = hind;
		this.summa = summa;
		this.perNimetus = perNimetus;
		this.perioodid = perioodid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArvestiid() {
		return arvestiid;
	}

	public void setArvestiid(Integer arvestiid) {
		this.arvestiid = arvestiid;
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

	public Integer getEnergia() {
		return energia;
	}

	public void setEnergia(Integer energia) {
		this.energia = energia;
	}

	public Integer getHind() {
		return hind;
	}

	public void setHind(Integer hind) {
		this.hind = hind;
	}

	public Integer getSumma() {
		return summa;
	}

	public void setSumma(Integer summa) {
		this.summa = summa;
	}	

	public String getPerNimetus() {
		return perNimetus;
	}

	public void setPerNimetus(String perNimetus) {
		this.perNimetus = perNimetus;
	}	

	public Integer getPerioodid() {
		return perioodid;
	}

	public void setPerioodid(Integer perioodid) {
		this.perioodid = perioodid;
	}

	@Override
	public String toString() {
		return "TarbitudEnergia [id=" + id + ", arvestiid=" + arvestiid + ", alates=" + alates + ", kuni=" + kuni
				+ ", energia=" + energia + ", hind=" + hind + ", summa=" + summa + "]";
	}	
	
}
