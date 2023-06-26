package net.code.station.viewmodel;

import java.sql.Date;
import java.sql.Timestamp;

import net.code.station.model.Contact;
import net.code.station.model.Pakkumine;
import net.code.station.model.Periood;
import net.code.station.model.Staatus;

public class AjaparingView {
	private Integer id;
	private Timestamp aeg;
    public AjaparingView() {
		
	}
	public AjaparingView(Integer id, Timestamp aeg) {
		this(aeg);
		this.id = id;
	}
	public AjaparingView(Timestamp aeg) {
		this.aeg = aeg;		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getAeg() {
		return aeg;
	}
	public void setAeg(Timestamp aeg) {
		this.aeg = aeg;
	}
	@Override
	public String toString() {
		return "AjaparingView [id=" + id + ", aeg=" + aeg + "]";
	}
	

}
