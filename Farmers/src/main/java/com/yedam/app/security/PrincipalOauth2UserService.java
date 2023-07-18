package com.yedam.app.security;

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
	
	/*
	 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	 */

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        OAuth2UserInfo oAuth2UserInfo = null;	//추가
        String provider = userRequest.getClientRegistration().getRegistrationId();    
        
        //추가
        if(provider.equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(provider.equals("naver")){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }
        
        String providerId = oAuth2UserInfo.getProviderId();	//수정
        String username = provider+"_"+providerId;  			

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid); 

        String email = oAuth2UserInfo.getEmail();	//수정
        //Role role = Role.ROLE_USER;
        
        MemberVO byUsername = memberMapper.selectMember(username);
        
        
		/*
		 * //DB에 없는 사용자라면 회원가입처리 if(byId == null){ byId = MemberVO.oauth2Register()
		 * .username(username).password(password).email(email).role(role)
		 * .provider(provider).providerId(providerId) .build();
		 * userRepository.save(byUsername); }
		 */
        return null;
        //return new PrincipalDetails(byUsername, oAuth2UserInfo);	//수정
    }
	
}
