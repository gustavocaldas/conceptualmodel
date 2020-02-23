package com.gcaldas.conceptualmodel.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.gcaldas.conceptualmodel.domain.PurcharseOrder;

public interface EmailService {

	void sendOrderConfirmationEmail(PurcharseOrder obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(PurcharseOrder obj);

	void sendHtmlEmail(MimeMessage msg);
}
