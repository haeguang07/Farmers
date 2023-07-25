package com.yedam.app.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		String test ="";
		Map<String, Object> map = new HashMap<>();
		map.put("test", "");
		adminMapper.updateMember(list,map);
		System.out.println(map);
	}



}
