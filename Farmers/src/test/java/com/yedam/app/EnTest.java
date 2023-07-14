package com.yedam.app;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootTest
public class EnTest {
	//@Test
	public void encTest() {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String password = scpwd.encode("1111");
		System.out.println(password);
		
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
		String enc=encryptor.encrypt("465");
		System.out.println(enc);
		String dec = encryptor.decrypt(enc);
		System.out.println(dec);
	}
}