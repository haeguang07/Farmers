package com.yedam.app;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class EnTest {
	@Autowired
	StringEncryptor jasyptStringEncryptor;
	//@Test
	public void encTest() {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String password = scpwd.encode("1111");
		System.out.println(password);
		boolean result=scpwd.matches("1111", "$2a$10$Tjz3ef.7QaMojTjHdQM2zeBgmZxy2mj0ZkYNpGxwV/k1tXmKhG3IK");
		System.out.println(result);
		
	}
	//@Test
	public void list() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add(null);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.toArray()[i]);
		}
		
	}
	@Test
	public void encTest2() {
		String enc=jasyptStringEncryptor.encrypt("");
		System.out.println(enc);
		String dnc= jasyptStringEncryptor.decrypt(enc);
		System.out.println(dnc);
		
	}


		
	
}
