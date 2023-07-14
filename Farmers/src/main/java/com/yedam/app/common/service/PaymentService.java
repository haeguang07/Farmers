package com.yedam.app.common.service;

import com.yedam.app.common.vo.MemberPayVO;

public interface PaymentService {
	// 결제 시 필요한 회원 정보 가져오기
	public MemberPayVO getMemberData(String memNo);
}
