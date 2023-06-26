package net.code.station.model;

public class Reegel {
	private Integer id;
	private String nimetus;
	private Boolean kehtib;
	private String kirjeldus;
	
    public Reegel() {
		
	}
	
	public Reegel(Integer id, String nimetus, Boolean kehtib, String kirjeldus) {		
		this(nimetus, kehtib, kirjeldus);
		this.id = id;
	}
	public Reegel(String nimetus, Boolean kehtib, String kirjeldus) {
		this.nimetus = nimetus;
		this.kehtib = kehtib;
		this.kirjeldus = kirjeldus;
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

	public Boolean getKehtib() {
		return kehtib;
	}

	public void setKehtib(Boolean kehtib) {
		this.kehtib = kehtib;
	}

	public String getKirjeldus() {
		return kirjeldus;
	}

	public void setKirjeldus(String kirjeldus) {
		this.kirjeldus = kirjeldus;
	}

	@Override
	public String toString() {
		return "Reegel [id=" + id + ", nimetus=" + nimetus + ", kehtib=" + kehtib + ", kirjeldus=" + kirjeldus + "]";
	}
	

}
