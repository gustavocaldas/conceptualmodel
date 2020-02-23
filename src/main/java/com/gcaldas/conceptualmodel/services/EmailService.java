package com.gcaldas.conceptualmodel.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.gcaldas.conceptualmodel.domain.PurchaseOrder;

public interface EmailService {

	void sendOrderConfirmationEmail(PurchaseOrder obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(PurchaseOrder obj);

	void sendHtmlEmail(MimeMessage msg);
}
