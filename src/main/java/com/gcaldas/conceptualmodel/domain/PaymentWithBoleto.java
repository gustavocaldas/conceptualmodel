package com.gcaldas.conceptualmodel.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gcaldas.conceptualmodel.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("paymentWithBoleto")
public class PaymentWithBoleto extends Payment {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date paymentDate;

	public PaymentWithBoleto() {

	}

	public PaymentWithBoleto(Integer id, PaymentStatus status, PurchaseOrder order, Date dueDate, Date paymentDate) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
