package com.vti.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
@EnableRedisHttpSession
public class RedisHttpSessionConfig {
	@Bean
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory(); 
	}

	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		return HeaderHttpSessionIdResolver.xAuthToken(); 
	}

}
