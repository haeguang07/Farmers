package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.yedam.app.**.mapper")
public class FarmersApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(FarmersApplication.class, args);
	}
	

}
