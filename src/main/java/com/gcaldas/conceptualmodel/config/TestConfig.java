package com.gcaldas.conceptualmodel.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gcaldas.conceptualmodel.services.DBService;
import com.gcaldas.conceptualmodel.services.EmailService;
import com.gcaldas.conceptualmodel.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbservice;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		
		dbservice.instantiateTestDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
