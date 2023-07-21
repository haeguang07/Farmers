package com.yedam.app.user.service;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yedam.app.security.PrincipalDetails;
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
		
		MemberVO vo = memberMapper.selectMember(username);
		System.out.println(vo);
		if(vo == null) {
			throw new UsernameNotFoundException("no User");
		}
		return new PrincipalDetails(vo);
	}
	//아이디로 조회
	@Override
	public MemberVO getMember(String id) {
		MemberVO vo = memberMapper.selectMember(id);
		return vo;
	}


	//아이디 중복
	@Override
	public boolean idCheck(String id) {
		String uid=memberMapper.selectId(id);
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
		if(uNick != null) {
			return false;			
		}else {
			return true;
		}
	}
	//이메일 중복
	@Override
	public boolean emailCheck(String email) {
		MemberVO vo = memberMapper.selectByEmail(email);
		if(vo==null) {
			return true;
		}else {
			return false;			
		}
	}


	//회원가입 기능(0714) //개인정보 암호화 해야함
	@Override
	public boolean join(MemberVO vo) {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		vo.setPw(scpwd.encode(vo.getPw()));

		int result=memberMapper.insertMember(vo);
		int result2 = memberMapper.insertMemberDetail(vo);
		if(result*result2>0) {
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
	//비밀번호 변경
	@Override
	public boolean pwChange(MemberVO vo) {
		
		return memberMapper.updatePw(vo)==1;
	}


	



	
}

