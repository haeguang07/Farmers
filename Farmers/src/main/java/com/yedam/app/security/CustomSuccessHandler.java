package com.yedam.app.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.yedam.app.common.service.AlertService;
import com.yedam.app.user.vo.AlertVO;
import com.yedam.app.user.vo.MemberVO;

public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	AlertService alertService;
	
	private final RequestCache requestCache = new HttpSessionRequestCache();
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	    clearSession(request);
		//세션 가져오기
		HttpSession session = request.getSession();
		//로그인 정보 세션에 저장하기
		MemberVO vo = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();
		if(vo!=null) {
			session.setAttribute("mem", vo);
			String memNo = vo.getMemNo();			
			List<AlertVO> list = alertService.myAlert(memNo);
			session.setAttribute("alertList", list);
		}
		//이전 페이지 정보 (로그인 페이지 이동시 전페이지 정보 세션에 저장)
		String prevPage = (String) request.getSession().getAttribute("prevPage");
        if (prevPage != null) {
        	//이전 페이지 url 변수에 저장후 세션 제거
            request.getSession().removeAttribute("prevPage");
        }
        //기본 url는 메인페이지
        String uri = "/";

		 
         //savedRequest 존재하는 경우 = 인증 권한이 없는 페이지 접근
         //Security Filter가 인터셉트하여 savedRequest에 세션 저장
	    SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();
        } else if (prevPage != null && !prevPage.equals("")) {
            // 회원가입(간편X                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ) - 로그인으로 넘어온 경우 "/"로 redirect
            if (prevPage.contains("join")) {
                uri = "/";
            } else {
                uri = prevPage;
            }
        }
        //만약 관리자가 로그인할 경우 관리자 페이지로 이동
        if(vo.getMemGrd()=="b0") {
        	uri="/admin";
        }
        redirectStrategy.sendRedirect(request, response, uri);
	}
	  // 로그인 실패 후 성공 시 남아있는 에러 세션 제거
	 protected void clearSession(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	        }
	    }
}
