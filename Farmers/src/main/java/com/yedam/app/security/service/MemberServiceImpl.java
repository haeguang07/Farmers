package com.yedam.app.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yedam.app.security.mapper.MemberMapper;
@Service
public class MemberServiceImpl implements MemberService, UserDetailsService{
	
	@Autowired
	MemberMapper memberMapper;
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		MemberVO vo = memberMapper.selectMember(username);
		System.out.println(vo);
		if(vo == null) {
			throw new UsernameNotFoundException("no User");
		}
		return vo;
	}
	
}

