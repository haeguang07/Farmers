package com.yedam.app.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.activity.vo.ExpVO;
import com.yedam.app.common.mapper.CodeMapper;
import com.yedam.app.common.vo.SearchVO;
import com.yedam.app.common.vo.WarnVO;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.MarketVO;
import com.yedam.app.user.mapper.AdminMapper;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.UpdateSttsVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminMapper;
	@Autowired
	CodeMapper codeMapper;

	// 회원
	@Override
	public List<MemberVO> getMemberList(SearchVO searchVO) {
		return adminMapper.selectMemberList(searchVO);
	}

	@Override
	public List<MemberVO> modifyMemberStts(List<MemberVO> list) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("test", "");
		adminMapper.updateMember(list, map);
		System.out.println(map);
		SearchVO vo = new SearchVO();
		return getMemberList(vo);
	}
	// 문의
	@Override
	public List<InquiryVO> getInqueryList(SearchVO vo) {
		return adminMapper.selectInqueryList(vo);
	}


	// 문의
	@Override
	public boolean replyInquiry(InquiryVO vo) {
		return adminMapper.updateInquiry(vo) == 1;
	}

	// 농지대여
	@Override
	public List<FarmLendVO> getFarmLendList() {
		return adminMapper.selectFarmLendList();
	}

	// 마켓
	@Override
	public List<MarketVO> getMarketList() {
		return adminMapper.selectMarketList();
	}

	// 비엔비
	@Override
	public List<BnbVO> getFarmBnbList() {
		return adminMapper.selectFarmBnbList();
	}

	// 경매장
	@Override
	public List<AuctionVO> getAuctionList() {
		return adminMapper.selectAuctionList();
	}

	// 펀딩
	@Override
	public List<FundingVO> getFundingList() {
		return adminMapper.selectFundingList();
	}
	//체험하기 
	@Override
	public List<ExpVO> getExpList() {
		return adminMapper.selectExpList();
	}
	
	// 신고
	@Override
	public List<WarnVO> getWarnList() {
		return adminMapper.selectWarnList();
	}

	// 동적으로 승인/거부
	@Override
	public Map<String, Object> chageStatus(List<UpdateSttsVO> list) {
		Map<String, Object> map = new HashMap<>();
		int total = 0, success = 0;
		for (UpdateSttsVO vo : list) {
			total++;
			success += adminMapper.updateStatus(vo);
		}
		map.put("total", total);
		map.put("success", success);
		if (success == 0) {
			map.put("retCode", "Fail");
		} else {
			map.put("retCode", "Success");
		}
		return map;
	}

}
