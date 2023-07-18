package com.yedam.app;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootTest
public class EnTest {
	//@Test
	public void encTest() {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String password = scpwd.encode("");
		System.out.println(password);
		boolean result=scpwd.matches("1111", "$2a$10$BmzLwRzZMXgphu8ScQFzZOSFYBMwGD8U5MO3aAXQWh1RwGuCHTdle");
		System.out.println(result);
		
	}
	
	@Test
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
		String enc=encryptor.encrypt("1111");
		System.out.println(enc);
		String dec = encryptor.decrypt(enc);
		System.out.println(dec);
	}
}
