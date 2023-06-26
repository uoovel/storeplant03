package net.code.station.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String hash = encoder.encode("User02+");
		System.out.println(hash);
	}

}
