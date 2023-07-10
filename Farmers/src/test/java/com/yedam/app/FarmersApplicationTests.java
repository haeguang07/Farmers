package com.yedam.app;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FarmersApplicationTests {
	@Value("${file.upload.path}") // 프로퍼티 혹은 빈에 있는 값들을 들고올 때 사용 (Spring value로 import)
	public String uploadPath;

	@Test
	void contextLoads() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 13;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) &&(i <= 33 || i >= 38)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		System.out.println(generatedString);

		System.out.println(uploadPath);
	}
	
}
