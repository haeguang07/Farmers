package com.yedam.app.user.service;

import com.yedam.app.user.vo.MemberVO;

public interface MemberService {
	// 중복체크
	public boolean idCheck(String id);
	public boolean nickCheck(String nick);
	//회원가입
	public boolean join(MemberVO vo);
	//아이디 찾기
	public String idFound(String email);
	//비밀번호 찾기
	public String pwFound(MemberVO vo);
	//비밀 번호 변경
	public boolean pwChange(MemberVO vo);
}

