package net.code.station.model;

import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private Integer id;
	
	private String uname;
	private String email;
	private String password;
	private Integer counter;
	public User() {
		
	}
	
	public User(Integer id, String uname, String email, String password, Integer counter) {		
		this(uname, email, password, counter);
		this.id = id;
	}
	public User(String uname, String email, String password, Integer counter) {
		this.uname = uname;
		this.email = email;
		this.password = password;
		this.counter = counter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", email=" + email + ", password=" + password + ", counter="
				+ counter + "]";
	}
	

}
