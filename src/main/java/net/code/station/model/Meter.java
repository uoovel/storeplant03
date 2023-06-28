package net.code.station.model;

public class Meter {
	private Integer id;
	private String number;
	private Integer peavoimsus;
	private Integer bilansihaldurid;
	private String ipaddress;
	
	public Meter() {
		
	}
	
	public Meter(Integer id, String number, Integer peavoimsus,
			Integer bilansihaldurid, String ipaddress) {		
		this(number, peavoimsus, bilansihaldurid, ipaddress);
		this.id = id;
		
	}
	public Meter(String number, Integer peavoimsus,
			Integer bilansihaldurid, String ipaddress) {
		this.number = number;
		this.peavoimsus = peavoimsus;
		this.bilansihaldurid = bilansihaldurid;
		this.ipaddress = ipaddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
		
	public Integer getPeavoimsus() {
		return peavoimsus;
	}

	public void setPeavoimsus(Integer peavoimsus) {
		this.peavoimsus = peavoimsus;
	}
	

	public Integer getEnergyProviderid() {
		return bilansihaldurid;
	}

	public void setEnergyProviderid(Integer bilansihaldurid) {
		this.bilansihaldurid = bilansihaldurid;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	@Override
	public String toString() {
		return "Meter [id=" + id + ", number=" + number + "]";
	}
	
}
