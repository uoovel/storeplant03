package net.code.station.model;

public class Bilansihaldur {
	private Integer id;
	private String nimetus;
	
	
    public Bilansihaldur() {
		
	}
	
	public Bilansihaldur(Integer id, String nimetus) {		
		this(nimetus);
		this.id = id;
	}
	public Bilansihaldur(String nimetus) {
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
	

}
