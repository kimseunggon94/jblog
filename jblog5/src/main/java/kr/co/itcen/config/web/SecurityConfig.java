package kr.co.itcen.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import kr.co.itcen.jblog.security.LoginInterceptor;
import kr.co.itcen.jblog.security.LogoutInterceptor;

/**
 * 1. Interceptor 설정
 * 2. Argument Resolver
 *
 */

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebMvcConfigurerAdapter {
	
	//Interceptors
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	@Bean
	public LogoutInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(loginInterceptor())
		.addPathPatterns("/user/auth");
		
		registry
		.addInterceptor(logoutInterceptor())
		.addPathPatterns("/user/logout");
		
		
		
	}
}
