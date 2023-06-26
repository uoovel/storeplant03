package net.code.station.config.security;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginCredentials {
	
	@NotEmpty(message="Palun sisesta kasutaja")
	private String userName;
	private String password;
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	public void setUserName(String userName2) {
		this.userName = userName;		
	}
	public void setPassword(String password2) {
		this.password = password;
		
	}
	
}
