package com.gcaldas.conceptualmodel.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcaldas.conceptualmodel.domain.PurcharseOrder;
import com.gcaldas.conceptualmodel.services.PurcharseOrderService;

@RestController
@RequestMapping(value = "/purcharseorders")
public class PurcharseOrderResource {

	@Autowired
	private PurcharseOrderService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PurcharseOrder> find(@PathVariable Integer id) {
		PurcharseOrder object = service.find(id);
		return ResponseEntity.ok().body(object);
	}
}
