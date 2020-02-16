package com.gcaldas.conceptualmodel.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.gcaldas.conceptualmodel.domain.Client;
import com.gcaldas.conceptualmodel.dto.ClientDTO;
import com.gcaldas.conceptualmodel.repositories.ClientRepository;
import com.gcaldas.conceptualmodel.resources.exceptions.FieldMessage;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	ClientRepository repClient;
	
	@Override
	public void initialize(UpdateClient ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		Client aux = repClient.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
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