package com.gcaldas.conceptualmodel.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcaldas.conceptualmodel.domain.Client;
import com.gcaldas.conceptualmodel.domain.enums.ClientType;
import com.gcaldas.conceptualmodel.dto.ClientNewDTO;
import com.gcaldas.conceptualmodel.repositories.ClientRepository;
import com.gcaldas.conceptualmodel.resources.exceptions.FieldMessage;
import com.gcaldas.conceptualmodel.services.validation.utils.BR;

public class InsertClientValidator implements ConstraintValidator<InsertClient, ClientNewDTO> {

	@Autowired
	ClientRepository repClient;
	
	@Override
	public void initialize(InsertClient ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getType().equals(ClientType.NATURALPERSON.getCod()) && !BR.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF invalid."));
		}
		
		if(objDto.getType().equals(ClientType.JURIDICALPERSON.getCod()) && !BR.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CNPJ invalid."));
		}
		
		Client aux = repClient.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email ja existente."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}