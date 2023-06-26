package net.code.station.config.security.handlers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.ModelAndView;

public class ApiAuthSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");			
		if(hasRole("ROLE_personal")) {
			response.sendRedirect("/Station01/personalWelcomeSec");
		}
        if(hasRole("ROLE_klient")) {
        	response.setHeader("userName", userName);
			response.sendRedirect("/Station01/userWelcomeSec?user="+userName);
		}	
	}
	
	//role parser
	public static boolean hasRole(String roleName)
	{
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority()
						.equals(roleName));
	}
	
	
	/*
	public ModelAndView gotoWelcome() {
		
		ModelAndView model = new ModelAndView();	
		model.setViewName("/personal/personalwelcome");
		return model;		
		
	}*/

}
