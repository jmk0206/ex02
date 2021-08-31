package org.zerock.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		logger.warn("Login Success");
		
		ArrayList<String> roleNames = new ArrayList<>();
		
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		logger.warn("ROLE NAMES: " + roleNames);
		
		if (roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/login/admin");
			return;
		}
		
		if (roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect("/login/member");
			return;
		}
		
		response.sendRedirect("/");
	}

}
