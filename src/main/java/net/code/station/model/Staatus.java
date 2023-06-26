package net.code.station.model;

public class Staatus {
	private Integer id;
	private String nimetus;
	
	public Staatus() {
		
	}
	
	public Staatus(Integer id, String nimetus) {		
		this(nimetus);
		this.id = id;
	}
	public Staatus(String nimetus) {
		this.nimetus = nimetus;
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

	@Override
	public String toString() {
		return nimetus;
	}
	

}
