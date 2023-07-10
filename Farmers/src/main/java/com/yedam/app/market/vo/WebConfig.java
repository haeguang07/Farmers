package com.yedam.app.market.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Bean
	MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Value("${file.upload.path}") // 프로퍼티 혹은 빈에 있는 값들을 들고올 때 사용 (Spring value로 import)
	public String uploadPath;
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/getFiles/**")
                .addResourceLocations("file:///c/upload/2023/07/10/");
    }
}
