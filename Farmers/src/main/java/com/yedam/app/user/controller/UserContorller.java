package com.yedam.app.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.security.PrincipalDetails;
import com.yedam.app.user.service.MemberService;

@Controller
public class UserContorller {
	@Autowired
	MemberService memberService;
	
	
	// 로그인 페이지 이동
	@GetMapping("login")
	public String login() {
		return "login/login";
	}

	// 아이디 비밀번호 찾기 이동
	@GetMapping("IDFound")
	public String IdFound() {
		return "login/IDPW";
	}

	// 회원가입 페이지 이동
	@GetMapping("join")
	public String join() {
		return "login/signup";
	}

	@GetMapping("/oauth/loginInfo")
    @ResponseBody
    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2UserPrincipal){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);
        // PrincipalOauth2UserService의 getAttributes내용과 같음

        Map<String, Object> attributes1 = oAuth2UserPrincipal.getAttributes();
        // attributes == attributes1

       return attributes.toString();     //세션에 담긴 user가져올 수 있음음
    }
    
    @GetMapping("/loginInfo")
    @ResponseBody
    public String loginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String result = "";

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        if(principal.getMemberVO().getLoginPath() == null) {
            result = result + "Form 로그인 : " + principal;
        }else{
            result = result + "OAuth2 로그인 : " + principal;
        }
        return result; 
    }

}
