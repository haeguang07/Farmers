package com.yedam.app.user.service;

import java.util.List;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
	public UserDetails loadUserByUsername(String username) throws InternalAuthenticationServiceException {
		
		MemberVO vo = memberMapper.selectMember(username);
		if(vo == null) {
			throw new InternalAuthenticationServiceException("존재하지 않는 아이디입니다");
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

		List<MemberVO> emailList=memberMapper.selectEmail();
		boolean result= true;
		for (MemberVO emails: emailList) {
			System.out.println("for:" +emails.getEmail());
			String decryptEmail=jasyptStringEncryptor.decrypt(emails.getEmail());
			if(decryptEmail.equals(email)) result=false;

		}
		return result;			
	}


	//회원가입 기능(0714) //개인정보 암호화 해야함
	@Override
	public boolean join(MemberVO vo) {
		//개인정보 암호화
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		vo.setPw(scpwd.encode(vo.getPw()));
		//다른 개인정보는 복호화를 위해 양방향으로
		vo.setZip(jasyptStringEncryptor.encrypt(vo.getZip()));
		vo.setAddr(jasyptStringEncryptor.encrypt(vo.getAddr()));
		vo.setDetaAddr(jasyptStringEncryptor.encrypt(vo.getDetaAddr()));
		vo.setMbl(jasyptStringEncryptor.encrypt(vo.getMbl()));
		vo.setEmail(jasyptStringEncryptor.encrypt(vo.getEmail()));
		//member에 삽입
		int result=memberMapper.insertMember(vo);
		//memberDetail에 삽입
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

