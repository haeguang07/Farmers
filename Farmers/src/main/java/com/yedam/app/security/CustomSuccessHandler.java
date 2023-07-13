package com.yedam.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.yedam.app.user.vo.MemberVO;

public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	    MemberVO vo = (MemberVO) authentication.getPrincipal();
	    if(vo!=null) {
	    	session.setAttribute("mem", vo);
	    }
		System.out.println("Success handler 실행");
		response.sendRedirect("/");
		
	}

}
