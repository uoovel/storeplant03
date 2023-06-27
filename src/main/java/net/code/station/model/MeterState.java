package net.code.station.model;

import java.sql.Timestamp;
import java.sql.Date;

import org.springframework.format.datetime.standard.DateTimeContext;

public class MeterState {
	private Integer id;	
	private Integer arvestinaitid;
	private Integer arvestiid;	
	private Timestamp aeg;
	private Integer energia;
	
	
	public MeterState() {
		
	}
	
	public MeterState(Integer id, Integer arvestinaitid, Integer arvestiid, Timestamp aeg, Integer energia) {		
		this(arvestinaitid, arvestiid, aeg, energia);
		this.id = id;
	}
	public MeterState(Integer arvestinaitid, Integer arvestiid, Timestamp aeg, Integer energia) {
		this.arvestinaitid = arvestinaitid;
		this.arvestiid = arvestiid;		
		this.aeg = aeg;
		this.energia = energia;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArvestinaitid() {
		return arvestinaitid;
	}

	public void setArvestinaitid(Integer arvestinaitid) {
		this.arvestinaitid = arvestinaitid;
	}

	public Integer getArvestiid() {
		return arvestiid;
	}

	public void setArvestiid(Integer arvestiid) {
		this.arvestiid = arvestiid;
	}

	public Timestamp getAeg() {
		return aeg;
	}

	public void setAeg(Timestamp aeg) {
		this.aeg = aeg;
	}

	public Integer getEnergia() {
		return energia;
	}

	public void setEnergia(Integer energia) {
		this.energia = energia;
	}

	@Override
	public String toString() {
		return "MeterState [id=" + id + ", arvestinaitid=" + arvestinaitid + ", arvestiid=" + arvestiid + ", aeg="
				+ aeg + ", energia=" + energia + "]";
	}
	
	
}
