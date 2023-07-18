package com.yedam.app.user.service;

import java.util.List;

import com.yedam.app.user.vo.AttachVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.PointsVO;

public interface MyPageService {
	// 비밀번호 확인
	public MemberVO checkPassword(String memNo);

	// 회원정보 가져오기
	public MemberVO getMemberInfo(String memNo);

	// 회원정보수정
	public void modifyMember(MemberVO vo);

	// 등업신청
	public boolean upgradeGrade(AttachVO vo);

	// 포인트 내역
	public List<PointsVO> pointHistory(String memNo);

	// 회원탈퇴
	public boolean secession(String memNo);
}