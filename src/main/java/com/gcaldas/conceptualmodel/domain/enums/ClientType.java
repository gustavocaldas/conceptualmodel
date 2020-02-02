package com.gcaldas.conceptualmodel.domain.enums;

public enum ClientType {

	NATURALPERSON(1, "Natural Person"), JURIDICALPERSON(2, "Juridical Person");

	private int cod;
	private String descricao;

	private ClientType(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static ClientType toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(ClientType x : ClientType.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + cod);
	}
	
}
