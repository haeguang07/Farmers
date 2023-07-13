package com.yedam.app.user.service;

public interface MemberService {
	// 중복체크
	public boolean idCheck(String id);
	public boolean nickCheck(String nick);
}

