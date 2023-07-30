package com.yedam.app;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootTest
public class EnTest {
	//@Test
	public void encTest() {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String password = scpwd.encode("1111");
		System.out.println(password);
		boolean result=scpwd.matches("1111", "$2a$10$Tjz3ef.7QaMojTjHdQM2zeBgmZxy2mj0ZkYNpGxwV/k1tXmKhG3IK");
		System.out.println(result);
		
	}
	@Test
	public void list() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add(null);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.toArray()[i]);
		}
		
	}
	
	//@Test
	public void test() {
		
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("ghpISMTKWv3TcnM9b0TtwqkFxe0NODviM0lmDE6");
		config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		String enc=encryptor.encrypt("");
		System.out.println(enc);
		String dec = encryptor.decrypt(enc);
		System.out.println(dec);
	}
}
