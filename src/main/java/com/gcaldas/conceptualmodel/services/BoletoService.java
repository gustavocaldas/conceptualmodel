package com.gcaldas.conceptualmodel.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.gcaldas.conceptualmodel.domain.PaymentWithBoleto;

@Service
public class BoletoService {
	
	public void fillPaymentWithBoleto(PaymentWithBoleto paid, Date orderDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderDate);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		paid.setDueDate(cal.getTime());
	}
}
