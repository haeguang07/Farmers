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

        String provider = userRequest.getClientRegistration().getRegistrationId();    //google
        String providerId ="";
        String email="";
        if(provider.equals("google")) {
        	providerId = oAuth2User.getAttribute("sub");
        	email = oAuth2User.getAttribute("email");
        }else if(provider.equals("naver")) {
        	providerId = ((Map<String, Object>) oAuth2User.getAttributes().get("response")).get("id").toString(); 
        	email= ((Map<String, Object>) oAuth2User.getAttributes().get("response")).get("email").toString(); 
        }
        String username = provider+"_"+providerId;  			// 사용자가 입력한 적은 없지만 만들어준다

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = scpwd.encode("패스워드"+uuid);  // 사용자가 입력한 적은 없지만 만들어준다

        
        MemberVO byEmail= memberMapper.selectByEmail(email);

        
        //DB에 없는 사용자라면 회원가입처리
        if(byEmail == null){
        	byEmail = new MemberVO();
        	byEmail.setEmail(email);
        	byEmail.setNick(username);
        	byEmail.setPw(password);
        	byEmail.setLoginPath(provider);
        	
        	byEmail.setId(providerId);
        	byEmail.setMemGrd("b0");
           memberMapper.insertMember(byEmail);
           memberMapper.insertMemberDetail(byEmail);
        }

        return new PrincipalDetails(byEmail, oAuth2User.getAttributes());
    }
	
}
