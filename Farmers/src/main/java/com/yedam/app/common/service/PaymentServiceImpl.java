package com.yedam.app.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.mapper.PaymentMapper;
import com.yedam.app.common.vo.MemberPayVO;
import com.yedam.app.common.vo.PayProductVO;
import com.yedam.app.common.vo.PaymentVO;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentMapper paymentMapper;
	
	@Override
	public MemberPayVO getMemberData(String memNo) {
		return paymentMapper.getMemberData(memNo);
	}

	@Override
	public PayProductVO getProductInfo(PayProductVO vo) {
		return paymentMapper.getProductInfo(vo);
	}

	@Override
	public void insertPaymnet(PaymentVO vo) {
		paymentMapper.insertPaymnet(vo);
	}

	@Override
	public void insertPayDetail(PayProductVO vo) {
		paymentMapper.insertPayDetail(vo);
	}

}
