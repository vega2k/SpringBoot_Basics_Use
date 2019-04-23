package com.vega2k.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 내부적으로 설정된 MVC 설정을 추가로 하고 싶을때  
 * 추가적인 설정 클래스를 만든다.
 */
@Configuration
//@EnableWebMvc  이 어노테이션을 사용하면 모든 mvc 설정을 개발자가 해야 하므로 설정하면 않된다.
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/m/**")
				.addResourceLocations("classpath:/m/")//반드시 m 다음에  / 을 주어야 한다.
				.setCachePeriod(20);//20초
	}
	
}
