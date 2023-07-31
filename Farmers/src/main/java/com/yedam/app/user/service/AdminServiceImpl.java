package com.yedam.app.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.activity.vo.ExpVO;
import com.yedam.app.common.mapper.CodeMapper;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.MarketVO;
import com.yedam.app.user.mapper.AdminMapper;
import com.yedam.app.user.vo.AdminSearchVO;
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
	public List<MemberVO> getMemberList(AdminSearchVO searchVO) {
		return adminMapper.selectMemberList(searchVO);
	}

	@Override
	public List<MemberVO> modifyMemberStts(List<MemberVO> list) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("test", "");
		adminMapper.updateMember(list, map);
		System.out.println(map);
		AdminSearchVO vo = new AdminSearchVO();
		return getMemberList(vo);
	}
	// 문의
	@Override
	public List<InquiryVO> getInqueryList(AdminSearchVO searchVO) {
		return adminMapper.selectInqueryList(searchVO);
	}


	// 문의
	@Override
	public boolean replyInquiry(InquiryVO vo) {
		return adminMapper.updateInquiry(vo) == 1;
	}

	// 농지대여
	@Override
	public List<FarmLendVO> getFarmLendList(AdminSearchVO searchVO) {
		return adminMapper.selectFarmLendList(searchVO);
	}

	// 마켓
	@Override
	public List<MarketVO> getMarketList(AdminSearchVO searchVO) {
		return adminMapper.selectMarketList(searchVO);
	}

	// 비엔비
	@Override
	public List<BnbVO> getFarmBnbList(AdminSearchVO searchVO) {
		return adminMapper.selectFarmBnbList(searchVO);
	}

	// 경매장
	@Override
	public List<AuctionVO> getAuctionList(AdminSearchVO searchVO) {
		return adminMapper.selectAuctionList(searchVO);
	}

	// 펀딩
	@Override
	public List<FundingVO> getFundingList(AdminSearchVO searchVO) {
		return adminMapper.selectFundingList(searchVO);
	}
	//체험하기 
	@Override
	public List<ExpVO> getExpList(AdminSearchVO searchVO) {
		return adminMapper.selectExpList(searchVO);
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
