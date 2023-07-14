package com.yedam.app.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.mapper.PaymentMapper;
import com.yedam.app.common.vo.MemberDetailVO;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentMapper paymentMapper;
	
	@Override
	public MemberDetailVO getMemberData(String memNo) {
		return paymentMapper.getMemberData(memNo);
	}

}
