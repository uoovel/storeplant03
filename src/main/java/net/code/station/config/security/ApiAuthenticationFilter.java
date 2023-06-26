package net.code.station.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import net.code.station.config.security.handlers.ApiAuthFailureHandler;
import net.code.station.config.security.handlers.ApiAuthSuccessHandler;

public class ApiAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	public ApiAuthenticationFilter(AuthenticationManager authenticationManager, String url) {
		super(url);
		// TODO Auto-generated constructor stub
		setAuthenticationManager(authenticationManager);
		setAuthenticationSuccessHandler(new ApiAuthSuccessHandler());
		setAuthenticationFailureHandler(new ApiAuthFailureHandler());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		//System.out.println("ApiAuthenticationFilter");
		LoginCredentials loginCredentials = new LoginCredentials();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		//System.out.println("userName: " + userName);
		loginCredentials.setUserName(userName);
		loginCredentials.setPassword(password);
		
		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(
						userName,
						password);
		
		/*
		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(
						"admin",
						"123");*/
		return getAuthenticationManager().authenticate(token);
	}

}
