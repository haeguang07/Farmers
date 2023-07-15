package com.yedam.app.common.mapper;

import com.yedam.app.common.vo.MemberPayVO;
import com.yedam.app.common.vo.PayProductVO;

public interface PaymentMapper {
	//결제 시 필요한 회원 정보 가져오기
	public MemberPayVO getMemberData(String memNo);
	
	//결제상품정보
	public PayProductVO getProductInfo(PayProductVO vo);
}
