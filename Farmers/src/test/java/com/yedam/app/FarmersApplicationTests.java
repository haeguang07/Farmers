package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FarmersApplicationTests {
	@Value("${file.upload.path}") // 프로퍼티 혹은 빈에 있는 값들을 들고올 때 사용 (Spring value로 import)
	public String uploadPath;

	@Test
	void contextLoads() {
		System.out.println(uploadPath);
	}
	
}
