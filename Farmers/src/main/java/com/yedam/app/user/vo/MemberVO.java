package com.yedam.app.user.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;

@Data
public class MemberVO implements UserDetails{
	
	private String memNo;
	private String id;
	private String pw;
	private String email;
	private String nick;
	private String loginPath;
	private String rptCnt;
	private String memGrd;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		if(memGrd.equals("b0")) {
			list.add(new SimpleGrantedAuthority("ROLE_REGULAR"));
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else if(memGrd.equals("b1")) {
			list.add(new SimpleGrantedAuthority("ROLE_REGULAR"));
		}else if(memGrd.equals("b2")) {
			list.add(new SimpleGrantedAuthority("ROLE_Associate"));
		}
		return list;
	}
	

	
	@Override
	public String getPassword() {
		return pw;
	}
	@Override
	public String getUsername() {
		return id;
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
	
}
