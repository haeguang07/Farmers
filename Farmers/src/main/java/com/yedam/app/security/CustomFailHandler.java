package com.yedam.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println(request.getParameter("username"));
		
		
		String errormsg= "pass";
		
		if(exception instanceof InternalAuthenticationServiceException ) {
			errormsg="id";
		}
		System.out.println("Fail handler 실행");
		response.sendRedirect("/login?error="+errormsg);
	}

}
