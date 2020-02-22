package com.gcaldas.conceptualmodel.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.gcaldas.conceptualmodel.domain.PurcharseOrder;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(PurcharseOrder obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPurcharseOrder(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromPurcharseOrder(PurcharseOrder obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed! Code: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
}
