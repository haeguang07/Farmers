package com.yedam.app.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.common.service.AlertService;
import com.yedam.app.user.vo.AlertVO;
import com.yedam.app.user.vo.MemberVO;

public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	AlertService alertService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	    MemberVO vo = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();
	    if(vo!=null) {
	    	session.setAttribute("mem", vo);
	    	String memNo = vo.getMemNo();			
			List<AlertVO> list = alertService.myAlert(memNo);
			session.setAttribute("alertList", list);
	    }
	    session.removeAttribute("errormsg");
		System.out.println("Success handler 실행");
		response.sendRedirect("/");
		
	}
}
