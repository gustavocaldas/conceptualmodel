package com.gcaldas.conceptualmodel.services;

import org.springframework.mail.SimpleMailMessage;

import com.gcaldas.conceptualmodel.domain.PurcharseOrder;

public interface EmailService {

	void sendOrderConfirmationEmail(PurcharseOrder obj);
	
	void sendEmail(SimpleMailMessage msg);
}
