package com.yedam.app.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.mapper.PaymentsMapper;
import com.yedam.app.common.vo.MemberPayVO;
import com.yedam.app.common.vo.PayProductVO;
import com.yedam.app.common.vo.PaymentDetailVO;
import com.yedam.app.common.vo.PaymentVO;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentsMapper paymentMapper;

	@Override
	public MemberPayVO getMemberData(String memNo) {
		return paymentMapper.getMemberData(memNo);
	}

	@Override
	public void getProductInfo(PaymentDetailVO vo) {
		paymentMapper.getProductInfo(vo);
	}

	@Override
	public void insertPaymnet(PaymentVO vo) {
		paymentMapper.insertPaymnet(vo);
	}

	@Override
	public void insertPayDetail(PayProductVO vo) {
		paymentMapper.insertPayDetail(vo);
	}

	@Override
	public PaymentVO getPayList(String payNo) {
		return paymentMapper.getPayList(payNo);
	}

	@Override
	public List<String> getPayMember(String boardNo) {
		return paymentMapper.getPayMember(boardNo);
	}

}
