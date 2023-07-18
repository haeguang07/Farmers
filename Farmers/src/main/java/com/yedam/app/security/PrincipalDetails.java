package com.yedam.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.yedam.app.user.vo.MemberVO;

import lombok.Getter;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {
	private MemberVO memberVO;
    private Map<String, Object> attributes;
    
    //UserDetails : Form 로그인 시 사용
    public PrincipalDetails(MemberVO memberVO) {
        this.memberVO = memberVO;
    }

    //OAuth2User : OAuth2 로그인 시 사용
    public PrincipalDetails(MemberVO memberVO, Map<String, Object> attributes) {
        //PrincipalOauth2UserService 참고
        this.memberVO = memberVO;
        this.attributes = attributes;
    }
    
    
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		if(memberVO.getMemGrd().equals("b0")) {
			list.add(new SimpleGrantedAuthority("ROLE_REGULAR"));
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		}else if(memberVO.getMemGrd().equals("b1")) {
			list.add(new SimpleGrantedAuthority("ROLE_REGULAR"));
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		}else if(memberVO.getMemGrd().equals("b2")) {
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	
		return list;
	}
	

	
	@Override
	public String getPassword() {
		return memberVO.getPw();
	}
	@Override
	public String getUsername() {
		return memberVO.getId();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}


	 /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public String getName() {
        String sub = attributes.get("sub").toString();
        return sub;
    }

}
