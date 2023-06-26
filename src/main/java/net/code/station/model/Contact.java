package net.code.station.model;

public class Contact {
	private Integer id;
	private String name;
	private String lastName;
	private String email;
	private Integer userid;
	
	public Contact() {
		
	}
	
	public Contact(Integer id, String name, String lastName, String email, Integer userid) {		
		this(name, lastName, email, userid);
		this.id = id;
	}
	public Contact(String name, String lastName, String email, Integer userid) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.userid = userid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return name + " " + lastName;
	}
	

}
