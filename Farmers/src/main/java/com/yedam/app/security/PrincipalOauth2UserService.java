package com.yedam.app.security;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.yedam.app.user.mapper.MemberMapper;
import com.yedam.app.user.vo.MemberVO;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	StringEncryptor jasyptStringEncryptor;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		//비밀번호 암호화
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		OAuth2User oAuth2User = super.loadUser(userRequest);

		OAuth2UserInfo oAuth2UserInfo = null;
		String provider = userRequest.getClientRegistration().getRegistrationId();
		String loginPath ="";
		// API 구분(네이버, 카카오)
		if (provider.equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
			loginPath="네이버";
		}else if(provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
            loginPath="카카오";
        }
		//API에서 얻은 정보들 
		String providerId = oAuth2UserInfo.getProviderId(); 
		String uuid = UUID.randomUUID().toString().substring(0, 6);
		String username = provider + "_" + uuid;
		String password = scpwd.encode("패스워드" + uuid);
		if(oAuth2UserInfo.getNick()!=null) {
			username= oAuth2UserInfo.getNick();
		}
		String name = oAuth2UserInfo.getName();
		String prof =oAuth2UserInfo.getProf();
		String email = oAuth2UserInfo.getEmail(); 
		String gender = oAuth2UserInfo.getGender();
		String id = provider.substring(0, 2)+"_"+ providerId;
		//이메일로 회원조회(이메일 복호화 후 비교)
		List<MemberVO> emailList=memberMapper.selectEmail();		
		String dbEmail = jasyptStringEncryptor.encrypt(email);
		String memNo="";
		for (MemberVO emails: emailList) {
			String decryptEmail=jasyptStringEncryptor.decrypt(emails.getEmail());
			if(decryptEmail.equals(email)) memNo= emails.getMemNo();
		}
		MemberVO vo = memberMapper.selectMemberByNo(memNo);
		// DB에 없는 사용자라면 회원가입처리
		if (vo == null) {
			vo = new MemberVO();
			vo.setEmail(dbEmail);vo.setNick(username);
			vo.setPw(password);vo.setZip(jasyptStringEncryptor.encrypt(""));
			vo.setAddr(jasyptStringEncryptor.encrypt(""));
			vo.setDetaAddr(jasyptStringEncryptor.encrypt(""));
			vo.setMbl(jasyptStringEncryptor.encrypt(""));
			vo.setLoginPath(loginPath);
			vo.setGen(gender);vo.setProf(prof);
			vo.setName(name);vo.setId(id);vo.setMemGrd("b2");
			memberMapper.insertMember(vo);memberMapper.insertMemberDetail(vo);
		}
		//로그인
		return new PrincipalDetails(vo, oAuth2User.getAttributes());
	}

}
