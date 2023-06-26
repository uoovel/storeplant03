package net.code.station.model;

import java.sql.Timestamp;

public class AMeetriNait {
	private Integer id;	
	private Integer ampermeeterid;	
	private Timestamp aeg;
	private Integer vool;
	
	
	public AMeetriNait() {
		
	}
	
	public AMeetriNait(Integer id, Integer ampermeeterid, Timestamp aeg, Integer vool) {		
		this(ampermeeterid, aeg, vool);
		this.id = id;
	}
	public AMeetriNait(Integer ampermeeterid, Timestamp aeg, Integer vool) {
		this.ampermeeterid = ampermeeterid;	
		this.aeg = aeg;
		this.vool = vool;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmpermeeterid() {
		return ampermeeterid;
	}

	public void setAmpermeeterid(Integer ampermeeterid) {
		this.ampermeeterid = ampermeeterid;
	}

	public Timestamp getAeg() {
		return aeg;
	}

	public void setAeg(Timestamp aeg) {
		this.aeg = aeg;
	}

	public Integer getVool() {
		return vool;
	}

	public void setVool(Integer vool) {
		this.vool = vool;
	}

	@Override
	public String toString() {
		return "AMeetriNait [id=" + id + ", ampermeeterid=" + ampermeeterid + ", aeg=" + aeg + ", vool=" + vool + "]";
	}
	

}
