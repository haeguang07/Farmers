package com.yedam.app.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.vo.SearchVO;
import com.yedam.app.user.mapper.AdminMapper;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper adminMapper;
	//회원
	@Override
	public List<MemberVO> getMemberList(SearchVO searchVO) {
		return adminMapper.selectMemberList(searchVO);
	}
	//문의
	@Override
	public List<InquiryVO> getInqueryList(SearchVO vo) {
		return adminMapper.selectInqueryList(vo);
	}
	@Override
	public void modifyMemberStts(List<MemberVO> list) {
		adminMapper.updateMember(list);
	}



}
