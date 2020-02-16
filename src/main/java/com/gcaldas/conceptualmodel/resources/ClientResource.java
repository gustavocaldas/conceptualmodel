package com.gcaldas.conceptualmodel.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gcaldas.conceptualmodel.domain.Client;
import com.gcaldas.conceptualmodel.dto.ClientDTO;
import com.gcaldas.conceptualmodel.dto.ClientNewDTO;
import com.gcaldas.conceptualmodel.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		Client object = service.find(id);
		return ResponseEntity.ok().body(object);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDTO) {
		Client obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDTO, @PathVariable Integer id) {
		Client obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> list = service.findAll();
		//Convertendo List<Client> para List<ClientDTO>
		List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Client> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> listDTO = list.map(obj -> new ClientDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
