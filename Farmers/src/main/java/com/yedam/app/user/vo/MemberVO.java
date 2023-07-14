package com.yedam.app.user.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MemberVO implements UserDetails{
	
	private String memNo;
	private String id;
	private String pw;
	private String email;
	private String nick;
	private String loginPath;
	private int rptCnt;
	private String memGrd;
	
	//Detail
	private String name;
	private int	zip;
	private String addr;
	private String detaAddr;
	private String mbl;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date bDate;
	private String gen;
	private int pnt;
	private String stts;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date signDate;
	private String prof;
	private String grdAtchFile;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date wdrAplDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date blckDate;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		if(memGrd.equals("b0")) {
			list.add(new SimpleGrantedAuthority("ROLE_REGULAR"));
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		}else if(memGrd.equals("b1")) {
			list.add(new SimpleGrantedAuthority("ROLE_REGULAR"));
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		}else if(memGrd.equals("b2")) {
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
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
