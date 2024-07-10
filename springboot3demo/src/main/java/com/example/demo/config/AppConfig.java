package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Phone;

@Configuration
public class AppConfig {
	
	@Bean
	Phone phone() {
		return new Phone(1001, "Apple", "Blue");
	}

}
