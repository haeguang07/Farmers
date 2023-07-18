package com.yedam.app.common.service;

import com.yedam.app.common.vo.MemberPayVO;
import com.yedam.app.common.vo.PayProductVO;
import com.yedam.app.common.vo.PaymentVO;

public interface PaymentService {
	// 결제 시 필요한 회원 정보 가져오기
	public MemberPayVO getMemberData(String memNo);

	// 결제상품정보
	public PayProductVO getProductInfo(PayProductVO vo);

	// 결제정보입력
	public void insertPaymnet(PaymentVO vo);

	// 결제상세정보입력
	public void insertPayDetail(PayProductVO vo);
}
