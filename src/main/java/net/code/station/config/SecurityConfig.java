package net.code.station.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import net.code.station.config.security.ApiAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.formLogin();
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/userWelcomeSec").authenticated();
		http.authorizeRequests().antMatchers("/vaataenarvlaud").authenticated();//yhine rollides
		http.authorizeRequests().antMatchers("/vaatatarbitudenergiaid").authenticated();//yhine rollides
		http.authorizeRequests().antMatchers("/minutellimused").authenticated();//yhine rollides
		http.authorizeRequests().antMatchers("/newtellimus").authenticated();
		http.authorizeRequests().antMatchers("/valiperioodtellimusele").authenticated();
		http.authorizeRequests().antMatchers("/savetellimus").authenticated();
		http.authorizeRequests().antMatchers("/deletetellimus").authenticated();
		http.authorizeRequests().antMatchers("/edittellimus").authenticated();//yhine rollides
		http.authorizeRequests().antMatchers("/naitaTellimusForm").authenticated();
		
		http.authorizeRequests().antMatchers("/personalWelcomeSec").authenticated();
		http.authorizeRequests().antMatchers("/personalWelcomeSec").hasRole("personal");
		http.authorizeRequests().antMatchers("/kliendid").authenticated();
		http.authorizeRequests().antMatchers("/kliendid").hasRole("personal");
		http.authorizeRequests().antMatchers("/psavetellimus").authenticated();
		http.authorizeRequests().antMatchers("/psavetellimus").hasRole("personal");
		http.authorizeRequests().antMatchers("/energiaarvutuslaud").authenticated();
		http.authorizeRequests().antMatchers("/energiaarvutuslaud").hasRole("personal");
		http.authorizeRequests().antMatchers("/arvutaenergiaraw").authenticated();
		http.authorizeRequests().antMatchers("/arvutaenergiaraw").hasRole("personal");
		http.authorizeRequests().antMatchers("/pakkumised").authenticated();
		http.authorizeRequests().antMatchers("/pakkumised").hasRole("personal");
		http.authorizeRequests().antMatchers("/newpakkumine").authenticated();
		http.authorizeRequests().antMatchers("/newpakkumine").hasRole("personal");
		http.authorizeRequests().antMatchers("/savepakkumine").authenticated();
		http.authorizeRequests().antMatchers("/savepakkumine").hasRole("personal");
		http.authorizeRequests().antMatchers("/editpakkumine").authenticated();
		http.authorizeRequests().antMatchers("/editpakkumine").hasRole("personal");
		http.authorizeRequests().antMatchers("/deletepakkumine").authenticated();
		http.authorizeRequests().antMatchers("/deletepakkumine").hasRole("personal");
		http.authorizeRequests().antMatchers("/ameetriNaidud").authenticated();
		http.authorizeRequests().antMatchers("/ameetriNaidud").hasRole("personal");
		http.authorizeRequests().antMatchers("/valiperioodvoimsuseleform").authenticated();
		http.authorizeRequests().antMatchers("/valiperioodvoimsuseleform").hasRole("personal");
		http.authorizeRequests().antMatchers("/valiperioodvoimsusele").authenticated();
		http.authorizeRequests().antMatchers("/valiperioodvoimsusele").hasRole("personal");
		http.authorizeRequests().antMatchers("/arvutaToovoimsus").authenticated();
		http.authorizeRequests().antMatchers("/arvutaToovoimsus").hasRole("personal");
		http.authorizeRequests().antMatchers("/ajalugu").authenticated();
		http.authorizeRequests().antMatchers("/ajalugu").hasRole("personal");
		http.authorizeRequests().antMatchers("/valiajaloodetailid").authenticated();
		http.authorizeRequests().antMatchers("/valiajaloodetailid").hasRole("personal");
		
		
		http.logout().logoutUrl("/Station01/");
		var apiLoginFilter = new ApiAuthenticationFilter(
				authenticationManager(), "/loginUserSec");
		http.addFilterAfter(apiLoginFilter, LogoutFilter.class);
	}
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.passwordEncoder(new BCryptPasswordEncoder())
		.withUser("user01")
		.password("$2a$10$XBiix6rQ9i1NLVqDMnsyyeCF46YfHX3SFv0zAxV6jgVf/abMEBvLC")
		.roles("klient")
		.and()
		.withUser("user02")
		.password("$2a$10$4Z/OdobY3rNAqU5huLNtNOxOlpPtg8XW3P6U4S2rODCCLQMgrrSWy")
		.roles("klient")
		.and()
		.withUser("personal01")
		.password("$2a$10$UqiQmdWaczUpEdDLoABtn.4uM0gLurBzE5udxz6bLkDGq1FB1N9aS")
		.roles("personal");
		
	}

}
