package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.common.service.PaymentService;
import com.yedam.app.common.vo.PaymentVO;


public class resultMapTest {

	
	@Test
	void test() {
		String fileName = "sky.jpg";
		String[] fileArray = fileName.split("\\.");
		System.out.println(fileArray[1]);
		String fileType = "."+fileArray[1];
		System.out.println(fileType);
	}
}
