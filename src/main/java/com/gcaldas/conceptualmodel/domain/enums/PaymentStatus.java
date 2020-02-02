package com.gcaldas.conceptualmodel.domain.enums;

public enum PaymentStatus {

	PENDING(1, "Pending"), 
	PAID(2, "Paid"),
	CANCELED(3, "Canceled");

	private int cod;
	private String descricao;

	private PaymentStatus(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static PaymentStatus toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(PaymentStatus x : PaymentStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + cod);
	}
	
}
