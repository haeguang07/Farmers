package com.yedam.app.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.vo.PaymentVO;
import com.yedam.app.user.mapper.MyPageMapper;
import com.yedam.app.user.vo.AttachVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.PointsVO;

@Service
public class MyPageServiceImpl implements MyPageService {
	@Autowired
	MyPageMapper myPageMapper;
	
	@Override
	public MemberVO checkPassword(String memNo) {
		return myPageMapper.checkPassword(memNo);
	}

	@Override
	public MemberVO getMemberInfo(String memNo) {
		return myPageMapper.getMemberInfo(memNo);
	}

	@Override
	public void modifyMember(MemberVO vo) {
		myPageMapper.modifyMember(vo);
	}

	@Override
	public List<PointsVO> pointHistory(String memNo) {
		return myPageMapper.pointHistory(memNo);
	}

	@Override
	public boolean upgradeGrade(AttachVO vo) {
		return myPageMapper.upgradeGrade(vo) >0;
	}

	@Override
	public boolean secession(String memNo) {
		return myPageMapper.secession(memNo) > 0;
	}

	@Override
	public PaymentVO myPayList(PaymentVO vo) {
		return myPageMapper.myPayList(vo);
	}

	@Override
	public List<PaymentVO> myPayNo(String memNo) {
		return myPageMapper.myPayNo(memNo);
	}

}
