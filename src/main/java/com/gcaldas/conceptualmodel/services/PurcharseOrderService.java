package com.gcaldas.conceptualmodel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcaldas.conceptualmodel.domain.Category;
import com.gcaldas.conceptualmodel.domain.PurcharseOrder;
import com.gcaldas.conceptualmodel.repositories.PurcharseOrderRepository;
import com.gcaldas.conceptualmodel.services.exceptions.ObjectNotFoundException;

@Service
public class PurcharseOrderService {

	@Autowired
	private PurcharseOrderRepository rep;

	public PurcharseOrder find(Integer id) {
		Optional<PurcharseOrder> obj = rep.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id: " + id + ", Type: " + PurcharseOrder.class.getName()));
	}
}
