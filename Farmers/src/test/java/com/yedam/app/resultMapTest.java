package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.common.service.PaymentService;
import com.yedam.app.common.vo.PaymentVO;

@SpringBootTest
public class resultMapTest {
	@Autowired
	PaymentService paymentService;
	
	@Test
	void test() {
		PaymentVO vo = paymentService.getPayList("pay0000000000101");
		System.out.println("11111111111111111111111");
		System.out.println(vo.getPaymentDetails());
	}
}
