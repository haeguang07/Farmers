package com.yedam.app.security;

import java.util.Map;
import java.util.UUID;

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

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		OAuth2User oAuth2User = super.loadUser(userRequest);

		OAuth2UserInfo oAuth2UserInfo = null;
		String provider = userRequest.getClientRegistration().getRegistrationId();

		
		if (provider.equals("google")) {
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		} else if (provider.equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
		}else if(provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

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


		MemberVO byEmail = memberMapper.selectByEmail(email);

		// DB에 없는 사용자라면 회원가입처리
		if (byEmail == null) {
			byEmail = new MemberVO();
			byEmail.setEmail(email);
			byEmail.setNick(username);
			byEmail.setPw(password);
			byEmail.setLoginPath(provider);
			byEmail.setGen(gender);
			byEmail.setProf(prof);
			byEmail.setName(name);
			byEmail.setId(providerId);
			byEmail.setMemGrd("b0");
			memberMapper.insertMember(byEmail);
			memberMapper.insertMemberDetail(byEmail);
		}

		return new PrincipalDetails(byEmail, oAuth2User.getAttributes());
	}

}
