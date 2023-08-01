package com.yedam.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errormsg= "pass";
		
		if(exception instanceof InternalAuthenticationServiceException ) {
			errormsg="id";
		}
		 HttpSession session = request.getSession();
		 session.setAttribute("errormsg", errormsg);
		System.out.println("Fail handler 실행");
		response.sendRedirect("/login?error="+errormsg);
	}

}
