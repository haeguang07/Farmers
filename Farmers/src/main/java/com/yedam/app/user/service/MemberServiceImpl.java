package com.yedam.app.user.service;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yedam.app.user.mapper.MemberMapper;
import com.yedam.app.user.vo.MemberVO;
@Service
public class MemberServiceImpl implements MemberService, UserDetailsService{
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	StringEncryptor jasyptStringEncryptor;


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


	//아이디 중복
	@Override
	public boolean idCheck(String id) {
		String uid=memberMapper.selectId(id);
		System.out.println(uid);
		if(uid != null) {
			return false;			
		}else {
			return true;
		}
	}
	//닉네임 중복
	@Override
	public boolean nickCheck(String nick) {
		String uNick=memberMapper.selectNick(nick);
		System.out.println(uNick);
		if(uNick != null) {
			return false;			
		}else {
			return true;
		}
	}


	//회원가입 기능(0714) //개인정보 암호화 해야함
	@Override
	public boolean join(MemberVO vo) {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String password = scpwd.encode(vo.getPw());
		vo.setPw(password);
		int result=memberMapper.insertMember(vo);
		int result2 = memberMapper.insertMemberDetail(vo);
		if(result*result2==1) {
			return true;
		}
		return false;
	}


	//아이디 찾기
	@Override
	public String idFound(String email) {
		return memberMapper.selectID(email);
	}
	@Override
	public String pwFound(MemberVO vo) {
		
	return memberMapper.selectPw(vo);
	}
	@Override
	public boolean pwChange(MemberVO vo) {
		
		return memberMapper.updatePw(vo)==1;
	}


	



	
}

