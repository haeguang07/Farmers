package com.yedam.app;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.common.service.CodeService;
import com.yedam.app.common.vo.CodeVO;

@SpringBootTest
public class FarmTest {
	@Autowired
	CodeService codeService;

	@Test
	public void test() {
		Map<String, List<CodeVO>> code = codeService.getCodes("0K", "0E", "k0", "k1", "k2", "k3", "k4", "k5", "k6",
				"k7", "k8");
		List<CodeVO> list = code.get("0E");
		
		int[] arr = { 7, 5, 4, 3, 2 };
		for (int i = 0; i < arr.length ; i++) {
			list.remove(arr[i]);		
		}

		System.out.println(list);
	}
}
