package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.common.vo.PaymentDetailVO;
import com.yedam.app.market.vo.FundingVO;

public interface FundingMapper {
	//펀딩 리스트 가져오기
	public List<FundingVO> getFundingList();
	
	//펀딩 리스트 가져오기(페이징)
	public List<FundingVO> getFundingListPage(int page,String category,String order,String search);
	
	//펀딩 총 갯수 가져오기
	public int fundingTotal(String category,String search);
	
	//단일 펀딩 정보 가져오기
	public FundingVO getFundingInfo(FundingVO vo);
	
	//추천 펀딩 정보 가져오기
	public List<FundingVO> getPolpularFnd();
	
	//펀딩 등록
	public int insertFunding(FundingVO vo);
	
	//펀딩 수정
	public int modifyFunding(FundingVO vo);
	
	//펀딩 실패 시 환불 정보
	public List<PaymentDetailVO> fundingRefundList();
	
	//펀딩 환불 처리 시 상태 변경
	public int updateRefundStts(String payDetaNo);
}
